package com.example.jpa.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BankAccountRequest {
    private String accountNo;
    private Long amount;

    @Builder
    public BankAccountRequest(String accountNo, Long amount) {
        this.accountNo = accountNo;
        this.amount = amount;
    }
}