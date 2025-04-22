package com.egorov.service.impl;

import com.egorov.exception.EmployeeNotFoundException;
import com.egorov.mapper.EmployeeMapper;
import com.egorov.repository.EmployeeRepository;
import com.egorov.service.EmployeeService;
import com.egorov.model.Employee;
import com.egorov.model.EmployeeDto;
import com.egorov.model.JobStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public EmployeeDto createEmployee(Employee employee) {
    employee.setJobStatus(JobStatus.NEW);
    return EmployeeMapper.toEmployeeDto(employeeRepository.save(employee));
  }

  @Override
  public EmployeeDto updateEmployee(Employee employee, Long employeeId) {
    Employee employeeById = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

    employeeById.setId(employee.getId());
    employeeById.setLastName(employee.getLastName());
    employeeById.setFirstName(employee.getFirstName());
    employeeById.setMiddleName(employee.getMiddleName());
    employeeById.setDepartment(employee.getDepartment());
    employeeById.setPhone(employee.getPhone());
    employeeById.setEmail(employee.getEmail());
    employeeById.setPosition(employee.getPosition());
    employeeById.setWorkSchedule(employee.getWorkSchedule());
    employeeById.setDateOfAdmission(employee.getDateOfAdmission());
    employeeById.setDateOfDismissal(employee.getDateOfDismissal());
    employeeById.setJobStatus(employee.getJobStatus());

    return EmployeeMapper.toEmployeeDto(employeeRepository.save(employeeById));
  }

  @Override
  public void deleteAllEmployees() {
    employeeRepository.deleteAll();
  }

  @Override
  public void deleteEmployeeById(Long employeeId) {
    employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    employeeRepository.deleteById(employeeId);
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    return EmployeeMapper.toEmployeeDtoList(employeeRepository.findAll());
  }


  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    return EmployeeMapper.toEmployeeDto(employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee not found")));
  }
}
