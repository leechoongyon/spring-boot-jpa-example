package com.example.jpa.repository;

import com.example.jpa.domain.SequenceStrategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SequenceStrategyRepository extends JpaRepository<SequenceStrategy, Long> {
}
