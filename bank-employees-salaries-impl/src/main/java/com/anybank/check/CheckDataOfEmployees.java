package com.anybank.check;

import com.anybank.enum_model.JobStatus;
import com.anybank.exception.EmployeeNotFoundException;
import com.anybank.repository.EmployeeRepository;
import com.anybank.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class CheckDataOfEmployees {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    public void checkData() {
        checkStatusEmployee();


    }

    private void checkStatusEmployee() {
        if (!employeeService.getEmployees().isEmpty()) {
            for (EmployeeDto employeeDto : employeeService.getEmployees()) {
                if (employeeDto.getDateOfAdmission().equals(LocalDateTime.now())) {
                    Employee employeeById = employeeRepository.findById(employeeDto.getId())
                            .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
                    employeeById.setJobStatus(JobStatus.WORKING);
                    employeeService.updateEmployee(employeeById, employeeById.getId());
                }
            }
        }
    }

}
