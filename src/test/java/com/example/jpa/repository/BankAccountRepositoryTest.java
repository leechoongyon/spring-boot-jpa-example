package com.example.jpa.repository;

import com.example.jpa.domain.BankAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void BankAccount_저장_테스트() throws Exception {

        // given
        BankAccount bankAccount = BankAccount.builder()
                .accountNo("123-123-123")
                .amount(10000L)
                .build();

        BankAccount bankAccount2 = BankAccount.builder()
                .accountNo("123-123-123")
                .amount(20000L)
                .build();

        bankAccountRepository.save(bankAccount);
        bankAccountRepository.save(bankAccount2);

        // when
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        // then
        Assert.assertThat(bankAccounts.size(), is(2));

    }
}