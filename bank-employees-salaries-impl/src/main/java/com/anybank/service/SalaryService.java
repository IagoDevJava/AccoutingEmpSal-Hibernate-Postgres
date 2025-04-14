package com.anybank.service;


import com.egorov.model.DateTimePeriod;
import com.egorov.model.Salary;
import com.egorov.model.SalaryDto;
import java.util.List;

public interface SalaryService {

  SalaryDto updateSalary(Salary salary, Long id);

  void deleteAllSalaries();

  void deleteSalaryById(Long id);

  SalaryDto getSalaryById(Long id);

  List<SalaryDto> getCompanySalariesByPeriod(DateTimePeriod dateTimePeriod);

  List<SalaryDto> getDepartmentSalariesByPeriod(Long departmentId, DateTimePeriod dateTimePeriod);

  SalaryDto getEmployeeSalaryByPeriod(Long employeeId, DateTimePeriod dateTimePeriod);

  List<SalaryDto> calculateDepartmentSalariesByPeriod(Long departmentId,
      DateTimePeriod dateTimePeriod);

  SalaryDto calculateEmployeeSalaryByPeriod(Long employeeId, DateTimePeriod dateTimePeriod);
}
