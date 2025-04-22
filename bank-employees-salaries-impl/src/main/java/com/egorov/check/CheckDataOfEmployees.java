package com.egorov.check;

import com.egorov.exception.EmployeeNotFoundException;
import com.egorov.repository.EmployeeRepository;
import com.egorov.service.EmployeeService;
import com.egorov.model.Employee;
import com.egorov.model.EmployeeDto;
import com.egorov.model.JobStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckDataOfEmployees {

  private EmployeeService employeeService;
  private EmployeeRepository employeeRepository;

  public void checkData() {
    checkStatusEmployee();
  }

  private void checkStatusEmployee() {
    if (!employeeService.getAllEmployees().isEmpty()) {
      for (EmployeeDto employeeDto : employeeService.getAllEmployees()) {
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
