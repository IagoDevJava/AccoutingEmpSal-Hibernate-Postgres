package com.anybank.repository;

import com.anybank.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KpiRepository extends JpaRepository<Kpi, Long> {
    Optional<Kpi> findByEmployeeAndMonthAndYear(Long empId, String month, String year);
}