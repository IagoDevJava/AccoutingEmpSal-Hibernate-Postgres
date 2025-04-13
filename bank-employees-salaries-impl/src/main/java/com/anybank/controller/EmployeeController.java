package com.anybank.controller;

import com.anybank.service.EmployeeService;
import com.egorov.api.EmployeeApi;
import com.egorov.model.Employee;
import com.egorov.model.EmployeeDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController implements EmployeeApi {

  private final EmployeeService employeeService;

  /**
   * Добавление сотрудника в БД
   */
  @Transactional
  public ResponseEntity<EmployeeDto> createEmployee(Employee employee) {
    return ResponseEntity.ok(employeeService.createEmployee(employee));
  }

  /**
   * Обновление сотрудника в БД
   */
  @Transactional
  public ResponseEntity<EmployeeDto> updateEmployee(Long employeeId, Employee employee) {
    return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeId));
  }

  /**
   * Удаление всех сотрудников из БД
   */
  @Transactional
  public ResponseEntity<Void> deleteAllEmployees() {
    employeeService.deleteAllEmployees();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаление сотрудника по id из БД
   */
  @Transactional
  public ResponseEntity<Void> deleteEmployeeById(Long employeeId) {
    employeeService.deleteEmployeeById(employeeId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Получение списка сотрудников из БД
   */
  @Transactional(readOnly = true)
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  /**
   * Получение сотрудника по id
   */
  @Transactional(readOnly = true)
  public ResponseEntity<EmployeeDto> getEmployeeById(Long employeeId) {
    return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
  }
}
