package com.anybank.controller;

import com.anybank.service.EmployeeService;
import com.egorov.api.EmployeeApi;
import com.egorov.model.Employee;
import com.egorov.model.EmployeeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с сотрудниками.
 * Обеспечивает REST API для создания, чтения, обновления и удаления сотрудников.
 * Реализует интерфейс {@link EmployeeApi}.
 */
@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

  private final EmployeeService employeeService;

  /**
   * Создает нового сотрудника и сохраняет его в БД
   *
   * @param employee данные сотрудника для создания
   * @return ResponseEntity с созданным сотрудником в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<EmployeeDto> createEmployee(Employee employee) {
    return ResponseEntity.ok(employeeService.createEmployee(employee));
  }

  /**
   * Обновляет данные существующего сотрудника в БД
   *
   * @param employeeId идентификатор сотрудника для обновления
   * @param employee новые данные сотрудника
   * @return ResponseEntity с обновленным сотрудником в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<EmployeeDto> updateEmployee(Long employeeId, Employee employee) {
    return ResponseEntity.ok(employeeService.updateEmployee(employee, employeeId));
  }

  /**
   * Удаляет всех сотрудников из БД
   *
   * @return ResponseEntity с пустым телом и статусом 204 NO_CONTENT
   */
  @Override
  public ResponseEntity<Void> deleteAllEmployees() {
    employeeService.deleteAllEmployees();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет сотрудника по его идентификатору из БД
   *
   * @param employeeId идентификатор сотрудника для удаления
   * @return ResponseEntity с пустым телом и статусом 204 NO_CONTENT
   */
  @Override
  public ResponseEntity<Void> deleteEmployeeById(Long employeeId) {
    employeeService.deleteEmployeeById(employeeId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает список всех сотрудников из БД
   *
   * @return ResponseEntity со списком сотрудников в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
    return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  /**
   * Возвращает сотрудника по его идентификатору
   *
   * @param employeeId идентификатор сотрудника
   * @return ResponseEntity с сотрудником в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<EmployeeDto> getEmployeeById(Long employeeId) {
    return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
  }
}