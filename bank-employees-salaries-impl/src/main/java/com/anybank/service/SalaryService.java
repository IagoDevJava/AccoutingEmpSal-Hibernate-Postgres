package com.anybank.service;


import com.egorov.model.DateTimePeriod;
import com.egorov.model.Salary;
import com.egorov.model.SalaryDto;
import java.util.List;

public interface SalaryService {

  /**
   * Сохранить данные о зарплате
   */
  SalaryDto addSalary(Salary salary);

  /**
   * Обновление данные о зарплате
   */
  SalaryDto updateSalary(Salary salary, Long id);

  /**
   * Удаление всех зарплат из БД
   */
  void deleteAllSalaries();

  /**
   * Удаление зарплат по id из БД
   */
  void deleteSalaryById(Long id);

  /**
   * получить данные о зарплате по id
   */
  SalaryDto getSalaryById(Long id);

  List<SalaryDto> getCompanySalariesByPeriod(DateTimePeriod dateTimePeriod);

  List<SalaryDto> getDepartmentSalariesByPeriod(Long departmentId, DateTimePeriod dateTimePeriod);

  SalaryDto getEmployeeSalaryByPeriod(Long employeeId, DateTimePeriod dateTimePeriod);
}
