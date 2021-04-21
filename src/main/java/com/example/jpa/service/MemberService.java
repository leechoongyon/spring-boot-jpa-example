package com.example.jpa.service;

import com.example.jpa.domain.Member;
import com.example.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Member> getMembers2() {
        List<Member> members = memberRepository.findAll();
        members.forEach(member -> {
            log.info("member : {}, order.size : {}", member, member.getAccounts().size());
        });
        return members;
    }
}
