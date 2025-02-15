package com.anybank.controller;

import com.anybank.dto.DepartmentDto;
import com.anybank.model.Department;
import com.anybank.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    /**
     * Добавление департамента в БД
     */
    @Transactional
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody Department department) {
        return ResponseEntity.ok(departmentService.addDepartment(department));
    }

    /**
     * Обновление департамента в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody Department department,
                                                          @PathVariable @PositiveOrZero Integer id) {
        return ResponseEntity.ok(departmentService.updateDepartment(department, id));
    }

    /**
     * Удаление всех департаментов из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteDepartment() {
        departmentService.deleteDepartment();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление департамента по id из БД
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PositiveOrZero @PathVariable Integer id) {
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка департаментов из БД
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartment() {
        return ResponseEntity.ok(departmentService.getDepartment());
    }

    /**
     * Получение департамента по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findDepartmentById(@PositiveOrZero @PathVariable Integer id) {
        return ResponseEntity.ok(departmentService.findDepartmentById(id));
    }
}
