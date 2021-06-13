package com.example.jpa;

import com.example.jpa.domain.Account;
import com.example.jpa.domain.Member;
import com.example.jpa.repository.AccountRepository;
import com.example.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

//@Component
@RequiredArgsConstructor
public class InitDatabase {

    private final MemberRepository memberRepository;

    private final AccountRepository accountRepository;

//    @PostConstruct
//    @Transactional
    public void initialize() {
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
}
