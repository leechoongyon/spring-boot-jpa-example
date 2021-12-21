package com.example.jpa.repository;

import com.example.jpa.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findByAccountNo(String accountNo);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from BankAccount b where b.accountNo = :accountNo")
    BankAccount findByAccountNoForUpdate(@Param("accountNo") String accountNo);
}