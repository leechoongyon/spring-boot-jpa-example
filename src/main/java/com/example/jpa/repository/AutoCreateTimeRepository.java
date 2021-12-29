package com.example.jpa.repository;

import com.example.jpa.domain.AutoCreateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoCreateTimeRepository extends JpaRepository<AutoCreateTime, Long> {
}
