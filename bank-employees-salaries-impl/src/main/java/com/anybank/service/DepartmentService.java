package com.anybank.service;

import com.egorov.model.Department;
import com.egorov.model.DepartmentDto;
import java.util.List;

public interface DepartmentService {

  /**
   * Добавление департамента в БД
   */
  DepartmentDto createDepartment(Department department);

  /**
   * Обновление департамента в БД
   */
  DepartmentDto updateDepartment(Long id, Department department);

  /**
   * Удаление всех департаментов из БД
   */
  void deleteAllDepartments();

  /**
   * Удаление департамента по id из БД
   */
  void deleteDepartmentById(Long id);

  /**
   * Получение списка департаментов из БД
   */
  List<DepartmentDto> getAllDepartments();

  /**
   * Получение департамента по id
   */
  DepartmentDto getDepartmentById(Long id);
}
