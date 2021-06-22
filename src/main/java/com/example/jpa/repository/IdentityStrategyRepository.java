package com.example.jpa.repository;

import com.example.jpa.domain.IdentityStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityStrategyRepository extends JpaRepository<IdentityStrategy, Long> {
}
