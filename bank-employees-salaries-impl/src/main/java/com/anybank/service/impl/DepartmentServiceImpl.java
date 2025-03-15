package com.anybank.service.impl;

import com.anybank.api.model.Department;
import com.anybank.api.model.DepartmentDto;
import com.anybank.exception.DepartmentNotFoundException;
import com.anybank.mapper.DepartmentMapper;
import com.anybank.repository.DepartmentRepository;
import com.anybank.service.DepartmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public DepartmentDto addDepartment(Department department) {
    return DepartmentMapper.toDepartmentDto(departmentRepository.save(department));
  }

  @Override
  public DepartmentDto updateDepartmentById(Long id, Department department) {
    Department departmentById = departmentRepository.findById(id)
        .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));

    departmentById.setId(department.getId());
    departmentById.setName(department.getName());
    departmentById.setAddress(department.getAddress());
    departmentById.setPhone(department.getPhone());
    departmentById.setEmail(department.getEmail());
    departmentById.setHeadId(department.getHeadId());

    return DepartmentMapper.toDepartmentDto(departmentRepository.save(departmentById));
  }

  @Override
  public void deleteDepartments() {
    departmentRepository.deleteAll();
  }

  @Override
  public void deleteDepartmentById(Long id) {
    departmentRepository.deleteById(id);
  }

  @Override
  public List<DepartmentDto> getDepartments() {
    return DepartmentMapper.toDepartmentDtoList(departmentRepository.findAll());
  }

  @Override
  public DepartmentDto getDepartmentById(Long id) {
    return DepartmentMapper.toDepartmentDto(departmentRepository.findById(id)
        .orElseThrow(() -> new DepartmentNotFoundException("Department not found")));
  }
}
