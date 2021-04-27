package com.example.jpa.api;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberResponse {
    private Long memberId;

    public MemberResponse(Long memberId) {
        this.memberId = memberId;
    }
}
