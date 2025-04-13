package com.anybank.repository;

import com.anybank.model.SalariesData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalariesDateRepository extends JpaRepository<SalariesData, Long> {
    Optional<SalariesData> findByPosition(Long id);
}