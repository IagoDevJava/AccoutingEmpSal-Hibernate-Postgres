package com.anybank.controller;

import com.anybank.service.DepartmentService;
import com.egorov.api.DepartmentApi;
import com.egorov.model.Department;
import com.egorov.model.DepartmentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentApi {

  private final DepartmentService departmentService;

  /**
   * Добавление департамента в БД
   */
  @Override
  public ResponseEntity<DepartmentDto> createDepartment(Department department) {
    return ResponseEntity.ok(departmentService.createDepartment(department));
  }

  /**
   * Удаление департамента по id из БД
   */
  @Override
  public ResponseEntity<Void> deleteDepartmentById(Long id) {
    departmentService.deleteDepartmentById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаление всех департаментов из БД
   */
  @Override
  public ResponseEntity<Void> deleteAllDepartments() {
    departmentService.deleteAllDepartments();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Получение департамента по id
   */
  @Override
  public ResponseEntity<DepartmentDto> getDepartmentById(Long id) {
    return ResponseEntity.ok(departmentService.getDepartmentById(id));
  }

  /**
   * Получение списка департаментов из БД
   */
  @Override
  public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
    return ResponseEntity.ok(departmentService.getAllDepartments());
  }

  /**
   * Обновление департамента в БД
   */
  @Override
  public ResponseEntity<DepartmentDto> updateDepartment(Long id, Department department) {
    return ResponseEntity.ok(departmentService.updateDepartment(id, department));
  }
}
