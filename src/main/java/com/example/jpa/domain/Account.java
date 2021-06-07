package com.example.jpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column @Setter
    private String accountNo;   // 계좌번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Account(String accountNo, Member member) {
        this.accountNo = accountNo;
        setMember(member);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
