package com.anybank.service;

import com.anybank.api.model.Department;
import com.anybank.api.model.DepartmentDto;
import java.util.List;

public interface DepartmentService {

  /**
   * Добавление департамента в БД
   */
  DepartmentDto addDepartment(Department department);

  /**
   * Обновление департамента в БД
   */
  DepartmentDto updateDepartmentById(Long id, Department department);

  /**
   * Удаление всех департаментов из БД
   */
  void deleteDepartments();

  /**
   * Удаление департамента по id из БД
   */
  void deleteDepartmentById(Long id);

  /**
   * Получение списка департаментов из БД
   */
  List<DepartmentDto> getDepartments();

  /**
   * Получение департамента по id
   */
  DepartmentDto getDepartmentById(Long id);
}
