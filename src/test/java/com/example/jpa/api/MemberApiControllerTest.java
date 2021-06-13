package com.example.jpa.api;

import com.example.jpa.JpaExampleApplication;
import com.example.jpa.domain.Account;
import com.example.jpa.domain.Member;
import com.example.jpa.repository.AccountRepository;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= JpaExampleApplication.class)
@AutoConfigureMockMvc
public class MemberApiControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MemberService memberService;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new MemberApiController(memberService))
                .build();

        // given
        Member member = Member.builder()
                .age(10)
                .name("Test1")
                .build();

        memberRepository.save(member);

        Account account = Account.builder()
                .member(member)
                .accountNo("123-456-7890")
                .build();

        accountRepository.save(account);

        Account account2 = Account.builder()
                .member(member)
                .accountNo("98395-3535-35353")
                .build();

        accountRepository.save(account2);




        // member2
        Member member2 = Member.builder()
                .age(20)
                .name("Test2")
                .build();

        memberRepository.save(member2);

        Account account3 = Account.builder()
                .member(member2)
                .accountNo("21398-13-1414124")
                .build();

        accountRepository.save(account3);

        Account account4 = Account.builder()
                .member(member2)
                .accountNo("49-13-513213-21")
                .build();

        accountRepository.save(account4);
    }

    /**
     * application.yml 에서 default fetch size 를 변경하거나 Member Entity 에서
     * @BatchSize 를 조절해주면 batch fetch 가 동작
     *
     * @throws Exception
     */
    @Test
    public void jpa_n_플러스_1_테스트() throws Exception {
        this.mockMvc.perform(get("/api/training2/members"))
                .andExpect(status().isOk());
    }

}