package com.egorov.repository;

import com.egorov.model.SalariesData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface SalariesDateRepository {
    Optional<SalariesData> findByPosition(Long id);
}