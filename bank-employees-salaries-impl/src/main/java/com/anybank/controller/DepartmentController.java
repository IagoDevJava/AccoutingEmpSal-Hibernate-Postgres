package com.anybank.controller;

import com.anybank.api.DepartmentApi;
import com.anybank.api.model.Department;
import com.anybank.api.model.DepartmentDto;
import com.anybank.service.DepartmentService;
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
  public ResponseEntity<DepartmentDto> addDepartment(Department department) {
    return ResponseEntity.ok(departmentService.addDepartment(department));
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
  public ResponseEntity<Void> deleteDepartments() {
    departmentService.deleteDepartments();
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
  public ResponseEntity<List<DepartmentDto>> getDepartments() {
    return ResponseEntity.ok(departmentService.getDepartments());
  }

  /**
   * Обновление департамента в БД
   */
  @Override
  public ResponseEntity<DepartmentDto> updateDepartmentById(Long id, Department department) {
    return ResponseEntity.ok(departmentService.updateDepartmentById(id, department));
  }
}
