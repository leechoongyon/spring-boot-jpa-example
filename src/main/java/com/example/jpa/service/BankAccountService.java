package com.example.jpa.service;

import com.example.jpa.domain.BankAccount;
import com.example.jpa.dto.BankAccountRequest;
import com.example.jpa.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public void withdraw(BankAccountRequest request) {
        BankAccount bankAccount =
                bankAccountRepository.findByAccountNo(request.getAccountNo());
        bankAccount.withdraw(request.getAmount());
    }

    @Transactional
    public void withdrawForUpdate(BankAccountRequest request) {
        BankAccount bankAccount =
                bankAccountRepository.findByAccountNoForUpdate(request.getAccountNo());
        bankAccount.withdraw(request.getAmount());
    }

    @Transactional
    public void deposit(BankAccountRequest request) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNo(request.getAccountNo());
        if (bankAccount == null) {
            BankAccount newBankAccount = BankAccount.of(request);
            bankAccountRepository.save(newBankAccount);
        } else {
            bankAccount.deposit(request);
        }
    }

    public Long getAmount(String accountNo) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNo(accountNo);
        return bankAccount == null ? 0L : bankAccount.getAmount();
    }
}