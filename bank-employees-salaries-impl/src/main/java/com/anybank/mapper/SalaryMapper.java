package com.anybank.mapper;

import com.anybank.dto.SalaryDto;
import com.anybank.model.Salary;
import java.util.ArrayList;
import java.util.List;

public class SalaryMapper {

  //Salary to SalaryDto
  public static SalaryDto toSalaryDto(Salary salary) {
    return SalaryDto.builder()
        .id(salary.getId())
        .employeeId(salary.getEmployee().getId())
        .departmentId(salary.getDepartment().getId())
        .month(salary.getMonth())
        .year(salary.getYear())
        .payment(salary.getPayment())
        .build();
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
