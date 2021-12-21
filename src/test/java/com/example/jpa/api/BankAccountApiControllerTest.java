package com.example.jpa.api;

import com.example.jpa.domain.BankAccount;
import com.example.jpa.dto.BankAccountRequest;
import com.example.jpa.repository.BankAccountRepository;
import com.example.jpa.service.BankAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String ACCOUNT_NO = "123-123-123";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Before
    public void setUp() {
        BankAccount bankAccount = BankAccount.builder()
                .accountNo(ACCOUNT_NO)
                .amount(1000000L)
                .build();
        bankAccountRepository.save(bankAccount);
    }

    @After
    public void clean() {
        bankAccountRepository.deleteAll();
    }

    @Test
    public void 인출_락없이_테스트() throws Exception {

        AtomicInteger successCount = new AtomicInteger();
        int numOfExecute = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(numOfExecute);

        // given
        for (int i = 0 ; i < numOfExecute ; i++) {
            executorService.execute(() -> {
                try {
                    BankAccountRequest request = BankAccountRequest.builder()
                            .accountNo(ACCOUNT_NO)
                            .amount(10000L)
                            .build();
                    bankAccountService.withdraw(request);

                    successCount.getAndIncrement();
                } catch (Throwable th) {
                    System.err.println(th);
                }

                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        // when
        BankAccount bankAccount = bankAccountRepository.findByAccountNo(ACCOUNT_NO);

        // then
        Assert.assertThat(successCount.get(),is(numOfExecute));
        Assert.assertNotEquals(bankAccount.getAmount(),is(0L));
    }

    @Test
    public void 인출_락걸고_테스트() throws Exception {
        int numOfExecute = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(numOfExecute);

        for (int i = 0 ; i < numOfExecute ; i++) {
            executorService.execute(() -> {
                try {
                    BankAccountRequest request = BankAccountRequest.builder()
                            .accountNo(ACCOUNT_NO)
                            .amount(10000L)
                            .build();
                    String content = objectMapper.writeValueAsString(request);

                    mockMvc.perform(MockMvcRequestBuilders.put("/account/withdrawal/useLock")
                                    .content(content)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andDo(MockMvcResultHandlers.print());
                } catch (Throwable th) {
                    System.err.println(th.getMessage());
                }
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        // then
        BankAccount bankAccount = bankAccountRepository.findById(1L).get();
        Assert.assertThat(bankAccount.getAmount(),is(0L));
    }
}