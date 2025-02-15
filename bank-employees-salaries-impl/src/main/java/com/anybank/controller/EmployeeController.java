package com.anybank.controller;

import com.anybank.dto.EmployeeDto;
import com.anybank.model.Employee;
import com.anybank.service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Добавление сотрудника в БД
     */
    @Transactional
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    }

    /**
     * Обновление сотрудника в БД
     */
    @Transactional
    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody Employee employee,
                                                      @PositiveOrZero @PathVariable Integer employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeId));
    }

    /**
     * Удаление всех сотрудников из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteEmployees() {
        employeeService.deleteEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление сотрудника по id из БД
     */
    @Transactional
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PositiveOrZero @PathVariable Integer employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка сотрудников из БД
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    /**
     * Получение сотрудника по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PositiveOrZero @PathVariable Integer employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }
}
