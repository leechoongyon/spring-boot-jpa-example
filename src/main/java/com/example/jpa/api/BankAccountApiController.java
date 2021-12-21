package com.example.jpa.api;

import com.example.jpa.dto.BankAccountRequest;
import com.example.jpa.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BankAccountApiController {

    private final BankAccountService bankAccountService;

    @PutMapping("/account/withdrawal/noLock")
    public ResponseEntity withdraw(@RequestBody BankAccountRequest request) {
        bankAccountService.withdraw(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/account/withdrawal/useLock")
    public ResponseEntity withdrawalForUpdate(@RequestBody BankAccountRequest request) {
        bankAccountService.withdrawForUpdate(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/account/deposit")
    public ResponseEntity deposit(@RequestBody BankAccountRequest request) {
        bankAccountService.deposit(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/account/{accountNo}")
    public ResponseEntity getAmount(@PathVariable("accountNo") String accountNo) {
        return ResponseEntity.ok(bankAccountService.getAmount(accountNo));
    }
}