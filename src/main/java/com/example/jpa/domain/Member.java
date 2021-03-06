package com.example.jpa.domain;


import com.example.jpa.dto.MemberRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    @Setter
    private Long id;

    @Column
    private String name;

    @Column
    private String telNo;

    @Column
    @ElementCollection
    private List<String> roles = new ArrayList<>();

    @Column
    private int age;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

//    @Version
//    private Long version;

    /** 양방향일 경우 연관관계 주인을 정해줘야 함.
     *  객체 참조는 둘인데 외래키는 한개이기에 어떤 외래키를 기준으로 수정, 삭제 할지를 결정해줘야 함. 그래야 JPA 가 그것을 보고 처리하지.
     *  주인은 수정,삭제 등을 할 수 있을테고 주인이 아닌 쪽은 읽기만 가능.
     *  연관관계의 주인은 보통 Many 쪽이 주인이 된다. 왜냐면 외래키가 Many 쪽에 있으니.
     *  이 때, 주의할게 JsonIgnore 를 왜 붙였냐면 API 를 통해서 Member 를 가져갈 때,
     *  Orders 를 제외한다는건데. 만약 이를 제거하지 않으면 stackoverflow 가 난다.
     *  왜냐면 내부에서 데이터를 가져올 때, hashcode 를 통해서 가져오는데 이를 따로 설정안해두면 똑같기에
     *  내부에서 계속 호출하면서 stackoverflow 가 발생. */
    @JsonIgnore
    @OneToMany(mappedBy = "member")
//    @BatchSize(size = 100)
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(String accountNo) {
        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }
        this.accounts.add(Account.builder()
                .accountNo(accountNo)
                .member(this)
                .build());
    }

    public void addAddress(Address address) {
        this.address = address;
        this.address.setMember(this);
    }

    @Builder
    public Member(Long id, String name, String telNo, int age, List<Account> accounts) {
        this.id = id;
        this.name = name;
        this.telNo = telNo;
        this.age = age;
        this.accounts = accounts;
    }


    public static Member create(MemberRequest request) {
        Member member = Member.builder()
                .name(request.getName())
                .telNo(request.getTelNo())
                .age(request.getAge())
                .build();
        return member;
    }

    public static Member createContainsId(MemberRequest request) {
        Member member = Member.builder()
                .id(7L)
                .name(request.getName())
                .telNo(request.getTelNo())
                .age(request.getAge())
                .build();
        return member;
    }
}
