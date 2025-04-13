package com.anybank.mapper;

import com.egorov.model.Department;
import com.egorov.model.DepartmentDto;
import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper {

  //Department to DepartmentDto
  public static DepartmentDto toDepartmentDto(Department department) {
    return new DepartmentDto()
        .id(department.getId())
        .name(department.getName())
        .phone(department.getPhone())
        .email(department.getEmail())
        .address(department.getAddress())
        .headId(department.getHeadId() != null ? department.getHeadId() : null);
  }

  //DepartmentList to DepartmentDtoList
  public static List<DepartmentDto> toDepartmentDtoList(List<Department> departments) {
    List<DepartmentDto> result = new ArrayList<>();
    for (Department department : departments) {
      result.add(toDepartmentDto(department));
    }
    return result;
  }
}
