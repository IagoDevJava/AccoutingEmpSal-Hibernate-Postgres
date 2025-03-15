package com.anybank.service;

import java.util.List;

public interface EmployeeService {

    /**
     * Добавление сотрудника в БД
     */
    EmployeeDto addEmployee(Employee employee);

    /**
     * Обновление сотрудника в БД
     */
    EmployeeDto updateEmployee(Employee employee, Integer employeeId);

    /**
     * Удаление всех сотрудников из БД
     */
    void deleteEmployees();

    /**
     * Удаление сотрудника по id из БД
     */
    void deleteEmployeeById(Integer employeeId);

    /**
     * Получение списка сотрудников из БД
     */
    List<EmployeeDto> getEmployees();

    /**
     * Получение сотрудника по id
     */
    EmployeeDto getEmployeeById(Integer employeeId);
}
