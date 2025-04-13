package com.anybank.controller;

import com.anybank.dto.SalariesDataDto;
import com.anybank.model.SalariesData;
import com.anybank.service.SalariesDataService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional(isolation = Isolation.READ_COMMITTED)
@RestController
@AllArgsConstructor
@RequestMapping("/salaries-dates")
public class SalariesDataController {
    private final SalariesDataService salariesDataService;

    /**
     * Добавить данные о зарплате в БД
     */
    @Transactional
    @PostMapping()
    public ResponseEntity<SalariesDataDto> addSalariesData(@Valid @RequestBody SalariesData salariesData) {
        return ResponseEntity.ok(salariesDataService.addSalariesData(salariesData));
    }

    /**
     * Заменить данные о зарплате в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<SalariesDataDto> updateSalariesData(@RequestBody SalariesData salariesData,
                                                              @PathVariable @PositiveOrZero Long id) {
        return ResponseEntity.ok(salariesDataService.updateSalariesData(salariesData, id));
    }

    /**
     * Удалить все данные зарплат из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteSalariesData() {
        salariesDataService.deleteSalariesData();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить данные зарплаты в БД по id
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalariesDataById(@PositiveOrZero @PathVariable Long id) {
        salariesDataService.deleteSalariesDataById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получить все данные зарплат в БД
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<SalariesDataDto>> getSalariesData() {
        return ResponseEntity.ok(salariesDataService.getSalariesData());
    }

    /**
     * Получить данные зарплаты в БД по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<SalariesDataDto> getSalariesDataById(@PositiveOrZero @PathVariable Long id) {
        return ResponseEntity.ok(salariesDataService.getSalariesDataById(id));
    }
}
