package com.egorov.controller;

import com.egorov.service.DepartmentService;
import com.egorov.api.DepartmentApi;
import com.egorov.model.Department;
import com.egorov.model.DepartmentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с департаментами.
 * Обеспечивает REST API для выполнения операций CRUD с департаментами.
 * Реализует интерфейс {@link DepartmentApi}.
 */
@RestController
@RequiredArgsConstructor
public class DepartmentController implements DepartmentApi {

  private final DepartmentService departmentService;

  /**
   * Создает новый департамент и сохраняет его в БД
   *
   * @param department Объект департамента для создания
   * @return ResponseEntity с созданным DepartmentDto и статусом OK (200)
   */
  @Override
  public ResponseEntity<DepartmentDto> createDepartment(Department department) {
    return ResponseEntity.ok(departmentService.createDepartment(department));
  }

  /**
   * Удаляет департамент по указанному ID из БД
   *
   * @param id ID департамента для удаления
   * @return ResponseEntity с пустым телом и статусом NO_CONTENT (204)
   */
  @Override
  public ResponseEntity<Void> deleteDepartmentById(Long id) {
    departmentService.deleteDepartmentById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет все департаменты из БД
   *
   * @return ResponseEntity с пустым телом и статусом NO_CONTENT (204)
   */
  @Override
  public ResponseEntity<Void> deleteAllDepartments() {
    departmentService.deleteAllDepartments();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает департамент по указанному ID
   *
   * @param id ID запрашиваемого департамента
   * @return ResponseEntity с DepartmentDto и статусом OK (200)
   */
  @Override
  public ResponseEntity<DepartmentDto> getDepartmentById(Long id) {
    return ResponseEntity.ok(departmentService.getDepartmentById(id));
  }

  /**
   * Возвращает список всех департаментов из БД
   *
   * @return ResponseEntity со списком DepartmentDto и статусом OK (200)
   */
  @Override
  public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
    return ResponseEntity.ok(departmentService.getAllDepartments());
  }

  /**
   * Обновляет данные департамента по указанному ID
   *
   * @param id ID департамента для обновления
   * @param department Объект с новыми данными департамента
   * @return ResponseEntity с обновленным DepartmentDto и статусом OK (200)
   */
  @Override
  public ResponseEntity<DepartmentDto> updateDepartment(Long id, Department department) {
    return ResponseEntity.ok(departmentService.updateDepartment(id, department));
  }
}