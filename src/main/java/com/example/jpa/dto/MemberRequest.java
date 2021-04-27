package com.example.jpa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberRequest {
    private Long memberId;
    private String name;
    private String telNo;
    private int age;
}
