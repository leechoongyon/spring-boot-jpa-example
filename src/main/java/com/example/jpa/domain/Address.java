package com.example.jpa.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    private String addr;

    @OneToOne(mappedBy = "address")
    private Member member;

    @Builder
    public Address(Long id, String addr, Member member) {
        this.id = id;
        this.addr = addr;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
