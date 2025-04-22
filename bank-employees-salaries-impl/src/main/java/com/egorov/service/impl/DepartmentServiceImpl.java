package com.egorov.service.impl;

import com.egorov.exception.DepartmentNotFoundException;
import com.egorov.mapper.DepartmentMapper;
import com.egorov.repository.DepartmentRepository;
import com.egorov.service.DepartmentService;
import com.egorov.model.Department;
import com.egorov.model.DepartmentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public DepartmentDto createDepartment(Department department) {
    return DepartmentMapper.toDepartmentDto(departmentRepository.save(department));
  }

  @Override
  public DepartmentDto updateDepartment(Long id, Department department) {
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
  public void deleteAllDepartments() {
    departmentRepository.deleteAll();
  }

  @Override
  public void deleteDepartmentById(Long id) {
    departmentRepository.deleteById(id);
  }

  @Override
  public List<DepartmentDto> getAllDepartments() {
    return DepartmentMapper.toDepartmentDtoList(departmentRepository.findAll());
  }

  @Override
  public DepartmentDto getDepartmentById(Long id) {
    return DepartmentMapper.toDepartmentDto(departmentRepository.findById(id)
        .orElseThrow(() -> new DepartmentNotFoundException("Department not found")));
  }
}
