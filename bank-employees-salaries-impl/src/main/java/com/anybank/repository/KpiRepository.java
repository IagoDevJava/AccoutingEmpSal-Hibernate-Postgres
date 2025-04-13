package com.anybank.repository;

import com.egorov.model.Kpi;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KpiRepository extends JpaRepository<Kpi, Long> {

  Optional<Kpi> findByEmployeeAndMonthAndYear(Long empId, String month, String year);
}