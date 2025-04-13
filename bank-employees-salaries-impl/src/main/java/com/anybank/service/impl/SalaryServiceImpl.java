package com.anybank.service.impl;

import com.anybank.exception.EmployeeNotFoundException;
import com.anybank.exception.KpiNotFoundException;
import com.anybank.exception.SalaryNotFoundException;
import com.anybank.mapper.SalaryMapper;
import com.anybank.repository.EmployeeRepository;
import com.anybank.repository.KpiRepository;
import com.anybank.repository.SalariesDateRepository;
import com.anybank.repository.SalaryRepository;
import com.anybank.service.SalaryService;
import com.egorov.model.DateTimePeriod;
import com.egorov.model.Employee;
import com.egorov.model.JobStatus;
import com.egorov.model.Kpi;
import com.egorov.model.Salary;
import com.egorov.model.SalaryDto;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {

  private final SalaryRepository salaryRepository;
  private final EmployeeRepository employeeRepository;
  private final SalariesDateRepository salariesDateRepository;
  private final KpiRepository kpiRepository;

  @Override
  public SalaryDto addSalary(Salary salary) {
    return SalaryMapper.toSalaryDto(salaryRepository.save(salary));
  }

  @Override
  public void deleteAllSalaries() {
    salaryRepository.deleteAll();
  }

  @Override
  public void deleteSalaryById(Long id) {
    salaryRepository.findById(id)
        .orElseThrow(() -> new SalaryNotFoundException("salary not found"));
    salaryRepository.deleteById(id);
  }

  @Override
  public SalaryDto getSalaryById(Long id) {
    return SalaryMapper.toSalaryDto(salaryRepository.findById(id)
        .orElseThrow(() -> new SalaryNotFoundException("salary not found")));
  }

  @Override
  public List<SalaryDto> getCompanySalariesByPeriod(DateTimePeriod dateTimePeriod) {
    return List.of();
  }

  @Override
  public List<SalaryDto> getDepartmentSalariesByPeriod(Long departmentId,
      DateTimePeriod dateTimePeriod) {
    return List.of();
  }

  @Override
  public SalaryDto getEmployeeSalaryByPeriod(Long employeeId, DateTimePeriod dateTimePeriod) {
    return null;
  }


  @Override
  public SalaryDto updateSalary(Salary salary, Long id) {
    Salary salaryById = salaryRepository.findById(id)
        .orElseThrow(() -> new SalaryNotFoundException("salary not found"));

    salaryById.setId(id);
    salaryById.setEmployee(salary.getEmployee());
    salaryById.setDepartment(salary.getDepartment());
    salaryById.setPeriod(salary.getPeriod());

    return SalaryMapper.toSalaryDto(salaryRepository.save(salaryById));
  }

//  @Override
//  public SalaryDto getSalaryByMonthForEmployee(Long employeeId, String month, String year) {
//    return SalaryMapper.toSalaryDto(
//        salaryRepository.findByEmployeeAndMonthAndYear(employeeId, month, year)
//            .orElseThrow(() -> new SalaryNotFoundException("salary not found")));
//  }
//
//
//  @Override
//  public List<SalaryDto> getSalaryByYearForEmployee(Long employeeId, String year) {
//    return SalaryMapper.toSalaryDtoList(salaryRepository.findByEmployeeAndYear(employeeId, year));
//  }
//
//
//  @Override
//  public List<SalaryDto> getSalaryByMonthForDepartment(Long departmentId, String month,
//      String year) {
//
//    return SalaryMapper.toSalaryDtoList(
//        salaryRepository.findByDepartmentAndMonthAndYear(departmentId, month, year)
//    );
//  }


//  @Override
//  public List<SalaryDto> getSalaryByYearForDepartment(Long departmentId, String year) {
//    return SalaryMapper.toSalaryDtoList(
//        salaryRepository.findByDepartmentAndYear(departmentId, year));
//  }
//
//
//  @Override
//  public List<SalaryDto> getSalaryByMonth(String month, String year) {
//    return SalaryMapper.toSalaryDtoList(salaryRepository.findByMonthAndYear(month, year));
//  }
//
//  @Override
//  public List<SalaryDto> getSalaryByYear(String year) {
//    return SalaryMapper.toSalaryDtoList(salaryRepository.findByYear(year));
//  }


//  @Override
//  public Salary calculateSalaryByMonthForEmployee(Long employeeId,
//      String month,
//      String year,
//      Integer countWorkDays,
//      Integer countMedDays) {
//    Employee employee = employeeRepository.findById(employeeId)
//        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
//
//    Double wageForEmpForPeriod = getWageForEmployeePeriod(employee, countWorkDays, countMedDays);
//    Double bonusForEmpForPeriod = getBonusForEmployeePeriod(employee, month, year);
//
//    return Salary.builder()
//        .employee(employee)
//        .month(month)
//        .year(year)
//        .payment(wageForEmpForPeriod + bonusForEmpForPeriod)
//        .build();
//  }

  private Double getWageForEmployeePeriod(Employee employee, Integer countWorkDays,
      Integer countMedicalDays) {
    //Получить оклад из БД
//        Position position = employee.getPosition();
//        SalariesData salariesData = salariesDateRepository
//                .findByPosition(position.getId()).orElseThrow(() -> new PositionNotFoundException("Position not found"));
//        Double wage = salariesData.getWage();
    Double wage = 1.1;
    //Получить оклад по отработанным дням
    double paymentForWorkDays = wage / LocalDate.now().lengthOfMonth() * countWorkDays;
    //Получить оклад по больничным дням
    double paymentForMedicalDays
        = wage / LocalDate.now().lengthOfMonth() * countMedicalDays
        * getRatioForMedDaysEmployeePeriod(employee);

    return paymentForWorkDays + paymentForMedicalDays;
  }

  private Double getBonusForEmployeePeriod(Employee employee, String month, String year) {
    //найти бонусную часть
//    Position position = employee.getPosition();
//    SalariesData salariesData = salariesDateRepository
//        .findByPosition(position.getId())
//        .orElseThrow(() -> new PositionNotFoundException("Position not found"));
//    Double bonus = salariesData.getBonus();
    Double bonus = 1.1;
    //найти kpi сотрудника за месяц
    Kpi kpiById = kpiRepository.findByEmployeeAndMonthAndYear(
            employee.getId(), month, year)
        .orElseThrow(() -> new KpiNotFoundException("Position not found"));

    return bonus -
        (bonus * kpiById.getPersonalKpi()
            + bonus * kpiById.getTeamKpi()
            + bonus * kpiById.getCommonKpi());
  }


  private Double getRatioForMedDaysEmployeePeriod(Employee employee) {
    double ratio = 0.0;
    if (employee.getJobStatus().equals(JobStatus.WORKING)) {
      LocalDate dateOfAdmission = employee.getDateOfAdmission();
      if (LocalDate.now().isAfter(dateOfAdmission.plusYears(2L))) {
        ratio = 1.0;
      } else if (LocalDate.now().isAfter(dateOfAdmission.plusYears(1L).plusMonths(6L))) {
        ratio = 0.8;
      } else if (LocalDate.now().isAfter(dateOfAdmission.plusYears(1L))) {
        ratio = 0.6;
      } else if (LocalDate.now().isAfter(dateOfAdmission.plusMonths(6L))) {
        ratio = 0.4;
      }
    }
    return ratio;
  }
}