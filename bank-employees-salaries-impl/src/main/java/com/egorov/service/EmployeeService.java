package com.egorov.service;

import com.egorov.model.Employee;
import com.egorov.model.EmployeeDto;
import java.util.List;

public interface EmployeeService {

  /**
   * Добавление сотрудника в БД
   */
  EmployeeDto createEmployee(Employee employee);

  /**
   * Обновление сотрудника в БД
   */
  EmployeeDto updateEmployee(Employee employee, Long employeeId);

  /**
   * Удаление всех сотрудников из БД
   */
  void deleteAllEmployees();

  /**
   * Удаление сотрудника по id из БД
   */
  void deleteEmployeeById(Long employeeId);

  /**
   * Получение списка сотрудников из БД
   */
  List<EmployeeDto> getAllEmployees();

  /**
   * Получение сотрудника по id
   */
  EmployeeDto getEmployeeById(Long employeeId);
}
