package com.example.jpa.domain;

import com.example.jpa.dto.BankAccountRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNo;
    private Long amount;

    @Builder
    public BankAccount(String accountNo, Long amount) {
        this.accountNo = accountNo;
        this.amount = amount;
    }

    public static BankAccount of(BankAccountRequest request) {
        return BankAccount.builder()
                .accountNo(request.getAccountNo())
                .amount(request.getAmount())
                .build();
    }

    public void withdraw(Long amount) {
        this.amount = this.amount - amount;
    }

    public void deposit(BankAccountRequest request) {
        this.amount = this.amount + request.getAmount();
    }
}