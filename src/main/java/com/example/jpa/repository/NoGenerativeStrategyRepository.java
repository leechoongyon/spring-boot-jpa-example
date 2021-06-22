package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import com.example.jpa.domain.NoGenerativeStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoGenerativeStrategyRepository extends JpaRepository<NoGenerativeStrategy, Long> {
}
