package com.example.jpa.repository;

import com.example.jpa.domain.Account;
import com.example.jpa.domain.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * RunWith 는 테스트를 실행시켜주는 것이고. SpringRunner 는 빈들을 올린다. SpringBootTest 와는 다르게 다 올리지는 않음.
 * DataJpaTest 는 인메모리 데이터베이스로 설정하고 엔티티 스캔.
 * 2개 Annotation 은 같이 써야 함. 스프링 공식문서에 나와있음.
 * reference : https://4whomtbts.tistory.com/128
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private final int SIZE = 100;

    @Test
    public void save_대량_성능_테스트() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < SIZE ; i++) {
            Member member = Member.builder()
                    .age(10)
                    .name("test")
                    .telNo("123")
                    .build();
            memberRepository.save(member);
        }
        System.out.println("elapsed time : "  + (System.currentTimeMillis() - start));
    }

    @Test
    public void saveAll_대량_성능_테스트() throws Exception {
        long start = System.currentTimeMillis();
        List<Member> members = new ArrayList<>();
        for (int i = 0 ; i < SIZE ; i++) {
            Member member = Member.builder()
                    .age(10)
                    .name("test")
                    .telNo("123")
                    .build();
            members.add(member);
        }
        memberRepository.saveAll(members);
        System.out.println("elapsed time : "  + (System.currentTimeMillis() - start));
    }

    /**
     * member, account 저장 테스트 (OneToMany 관계)
     * @throws Exception
     */
    @Test
    public void MEMBER_ACCOUNT_ONE_TO_MANY_저장_테스트() throws Exception {

        // given
        Member member = Member.builder()
                .age(10)
                .name("test")
                .telNo("123")
                .build();
        member.addAccount("123123123");

        memberRepository.save(member);

        // when
        List<Member> list = memberRepository.findAll();
        List<Account> accounts = list.get(0).getAccounts();
        String accountNo = accounts.get(0).getAccountNo();

        // then
        Assert.assertThat(accountNo, is("123123123"));
    }
}