package com.example.jpa.repository;


import com.example.jpa.domain.SampleWithAutoIncrement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleWithAutoIncrementRepository extends JpaRepository<SampleWithAutoIncrement, Long> {
}
