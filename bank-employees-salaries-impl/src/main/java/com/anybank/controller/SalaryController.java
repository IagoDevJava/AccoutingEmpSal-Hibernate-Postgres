package com.anybank.controller;

import com.anybank.dto.SalaryDto;
import com.anybank.model.Salary;
import com.anybank.service.SalaryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Transactional(isolation = Isolation.READ_COMMITTED)
@RestController
@AllArgsConstructor
@RequestMapping("/salaries")
public class SalaryController {
    private final SalaryService salaryService;

    /**
     * Сохранить данные о зарплате
     */
    @Transactional
    @PostMapping()
    public ResponseEntity<SalaryDto> addSalary(@RequestBody @Valid Salary salary) {
        return ResponseEntity.ok(salaryService.addSalary(salary));
    }

    /**
     * Обновление данные о зарплате
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<SalaryDto> updateSalary(@RequestBody Salary salary,
                                                  @PathVariable @PositiveOrZero Long id) {
        return ResponseEntity.ok(salaryService.updateSalary(salary, id));
    }

    /**
     * Рассчитать данные о зарплате по сотруднику за месяц
     */
    @Transactional
    @GetMapping("/calculate/{employeeId}")
    public ResponseEntity<Salary> calculateSalaryByMonthForEmployee(
            @PathVariable @PositiveOrZero Integer employeeId,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year,
            @RequestParam @PositiveOrZero @NotNull Integer countWorkDays,
            @RequestParam @PositiveOrZero @NotNull Integer countMedDays) {
        return ResponseEntity.ok(salaryService.calculateSalaryByMonthForEmployee(
                employeeId,
                Objects.requireNonNullElse(month, String.valueOf(LocalDate.now().getMonth())),
                Objects.requireNonNullElse(year, String.valueOf(LocalDate.now().getYear())),
                countWorkDays,
                countMedDays)
        );
    }

    /**
     * Удаление всех зарплат
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteSalary() {
        salaryService.deleteSalary();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление зарплат по id из БД
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalaryById(@PositiveOrZero @PathVariable Long id) {
        salaryService.deleteSalaryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * получить данные о зарплате по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<SalaryDto> getSalaryById(@PositiveOrZero @PathVariable Long id) {
        return ResponseEntity.ok(salaryService.getSalaryById(id));
    }

    /**
     * получить данные о зарплате по сотруднику за месяц
     */
    @Transactional(readOnly = true)
    @GetMapping("/{employeeId}/month")
    public ResponseEntity<SalaryDto> getSalaryByMonthForEmployee(@PositiveOrZero @PathVariable Integer employeeId,
                                                                 @RequestParam(required = false) String month,
                                                                 @RequestParam(required = false) String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonthForEmployee(
                employeeId,
                Objects.requireNonNullElse(month, String.valueOf(LocalDate.now().getMonth())),
                Objects.requireNonNullElse(year, String.valueOf(LocalDate.now().getYear()))));
    }

    /**
     * получить данные о зарплате по сотруднику за год
     */
    @Transactional(readOnly = true)
    @GetMapping("/{employeeId}/year")
    public ResponseEntity<List<SalaryDto>> getSalaryByYearForEmployee(@PositiveOrZero @PathVariable Integer employeeId,
                                                                      @RequestParam(required = false) String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYearForEmployee(
                employeeId,
                Objects.requireNonNullElse(year, String.valueOf(LocalDate.now().getYear())))
        );
    }

    /**
     * получить данные о зарплате по отделу за месяц
     */
    @Transactional(readOnly = true)
    @GetMapping("/{departmentId}/month")
    public ResponseEntity<List<SalaryDto>> getSalaryByMonthForDepartment(
            @PositiveOrZero @PathVariable Integer departmentId,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonthForDepartment(
                departmentId,
                Objects.requireNonNullElse(month, String.valueOf(LocalDate.now().getMonth())),
                Objects.requireNonNullElse(year, String.valueOf(LocalDate.now().getYear()))));
    }

    /**
     * получить данные о зарплате по отделу за год
     */
    @Transactional(readOnly = true)
    @GetMapping("/{departmentId}/year")
    public ResponseEntity<List<SalaryDto>> getSalaryByYearForDepartment(
            @PositiveOrZero @PathVariable Integer departmentId,
            @RequestParam(required = false) String year) {
        return ResponseEntity.ok(salaryService.getSalaryByYearForDepartment(departmentId,
                Objects.requireNonNullElse(year, String.valueOf(LocalDate.now().getYear())))
        );
    }

    /**
     * получить данные о зарплате по компании за месяц
     */
    @Transactional(readOnly = true)
    @GetMapping("/month")
    public ResponseEntity<List<SalaryDto>> getSalaryByMonth(@RequestParam(required = false) String month,
                                                            @RequestParam(required = false) String year) {
        return ResponseEntity.ok(salaryService.getSalaryByMonth(
                Objects.requireNonNullElse(month, String.valueOf(LocalDate.now().getMonth())),
                Objects.requireNonNullElse(year, String.valueOf(LocalDate.now().getYear())))
        );
    }

    /**
     * получить данные о зарплате по компании за год
     */
    @Transactional(readOnly = true)
    @GetMapping("/year")
    public ResponseEntity<List<SalaryDto>> getSalaryByYear(@RequestParam(required = false) String year) {
        return ResponseEntity.ok(
                salaryService.getSalaryByYear(Objects.requireNonNullElse(
                        year, String.valueOf(LocalDate.now().getYear()))
                )
        );
    }
}
