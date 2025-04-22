package com.egorov.mapper;

import com.egorov.model.Salary;
import com.egorov.model.SalaryDto;
import java.util.ArrayList;
import java.util.List;

public class SalaryMapper {

  //Salary to SalaryDto
  public static SalaryDto toSalaryDto(Salary salary) {
    return new SalaryDto()
        .id(salary.getId())
        .employeeId(salary.getEmployee().getId())
        .departmentId(salary.getDepartment().getId())
        .period(salary.getPeriod());
  }

  //SalaryList to SalaryDtoList
  public static List<SalaryDto> toSalaryDtoList(List<Salary> salaries) {
    List<SalaryDto> result = new ArrayList<>();
    for (Salary salary : salaries) {
      result.add(toSalaryDto(salary));
    }
    return result;
  }
}
