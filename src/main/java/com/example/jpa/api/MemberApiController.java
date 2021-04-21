package com.example.jpa.api;


import com.example.jpa.domain.Member;
import com.example.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    /**
     * osiv 테스트 api
     * osiv 옵션이 꺼져있으면 account 가져올 때 에러남.
     * MemberService 의 @Transaction 이 종료된 순간 영속성 컨텍스트가 종료되기에.
     * @return
     */
    @GetMapping("/api/training1/members")
    public List<Member> getMembers1() {
        List<Member> list = memberService.getMembers();

        list.forEach(member -> {
            log.info("account.size : {}", member.getAccounts().size());
        });

        return list;
    }

    /**
     * n+1 테스트 용도
     * 데이터는 member 2명, Account 는 각 2개씩 있음
     *
     * 1. Account 쪽에 lazy loading 이 걸려있으면 현재 쿼리 3번 부름.
     * 2. Account 쪽에 eager 가 걸려있어도 쿼리 3번 부름.
     *
     * 해결방안은 application.yml 에 spring.jpa.properties.hibernate.default_batch_fetch_size 설정해주면 됨.
     * 해당 옵션 걸어주면 Member 가 Account 조회할 때, inQuery 로 한 번에 조회해 옴.
     *
     * @return
     */
    @GetMapping("/api/training2/members")
    public List<Member> getMembers2() {
        return memberService.getMembers2();
    }
}
