package com.egorov.mapper;

import com.egorov.model.Employee;
import com.egorov.model.EmployeeDto;
import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

  //Employee to EmployeeDto
  public static EmployeeDto toEmployeeDto(Employee employee) {
    return new EmployeeDto()
        .id(employee.getId())
        .lastName(employee.getLastName())
        .firstName(employee.getFirstName())
        .middleName(employee.getMiddleName())
        .position(employee.getPosition() != null ? employee.getPosition() : null)
        .department(employee.getDepartment() != null ? employee.getDepartment() : null)
        .phone(employee.getPhone())
        .email(employee.getEmail())
        .workSchedule(employee.getWorkSchedule() != null ? employee.getWorkSchedule() : null)
        .dateOfAdmission(employee.getDateOfAdmission())
        .jobStatus(employee.getJobStatus())
        .dateOfDismissal(employee.getDateOfDismissal());
  }

  //EmployeeList to EmployeeDtoList
  public static List<EmployeeDto> toEmployeeDtoList(List<Employee> employees) {
    List<EmployeeDto> result = new ArrayList<>();
    for (Employee employee : employees) {
      result.add(toEmployeeDto(employee));
    }
    return result;
  }
}
