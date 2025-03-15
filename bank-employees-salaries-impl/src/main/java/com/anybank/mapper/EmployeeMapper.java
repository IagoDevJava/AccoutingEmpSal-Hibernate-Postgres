package com.anybank.mapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {
    //Employee to EmployeeDto
    public static EmployeeDto toEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .surname(employee.getSurname())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .positionId(employee.getPosition() != null ? employee.getPosition().getId() : null)
                .gender(employee.getGender())
                .departmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null)
                .phone(employee.getPhone())
                .email(employee.getEmail())
                .workScheduleId(employee.getWorkSchedule() != null ? employee.getWorkSchedule().getId() : null)
                .dateOfAdmission(employee.getDateOfAdmission())
                .jobStatus(employee.getJobStatus())
                .dateOfDismissal(employee.getDateOfDismissal())
                .build();
    }

    //EmployeeList to EmployeeDtoList
    public static List<EmployeeDto> toEmployeeDtoList(List<Employee> employees) {
        List<EmployeeDto> result = new ArrayList<>();
        for (Employee employee : employees) {
            result.add(toEmployeeDto(employee));
        }
        return result;
    }

//    //EmployeeDto to Employee
//    public static Employee toEmployee(EmployeeDto employeeDto) {
//        return Employee.builder()
//                .id(employeeDto.getId())
//                .surname(employeeDto.getSurname())
//                .firstname(employeeDto.getFirstname())
//                .lastname(employeeDto.getLastname())
//                .position(employeeDto.getPositionId())
//                .gender(employeeDto.getGender())
//                .departmentId(employeeDto.getDepartmentId())
//                .phone(employeeDto.getPhone())
//                .email(employeeDto.getEmail())
//                .workScheduleId(employeeDto.getWorkScheduleId())
//                .dateOfAdmission(employeeDto.getDateOfAdmission())
//                .jobStatus(employeeDto.getJobStatus())
//                .dateOfDismissal(employeeDto.getDateOfDismissal())
//                .build();
//    }
}
