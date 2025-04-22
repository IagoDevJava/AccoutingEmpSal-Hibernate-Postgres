package com.egorov.controller;

import com.egorov.service.GradeService;
import com.egorov.api.GradeApi;
import com.egorov.model.Grade;
import com.egorov.model.GradeDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с грейдами сотрудников.
 * Обеспечивает REST API для создания, обновления, удаления и получения информации о грейдах.
 * Реализует интерфейс {@link GradeApi}.
 */
@RestController
@RequiredArgsConstructor
public class GradeController implements GradeApi {

  private final GradeService gradeService;

  /**
   * Создает новый грейд и сохраняет его в базе данных
   *
   * @param grade Объект грейда для создания
   * @return ResponseEntity с созданным объектом GradeDto и статусом 200 (OK)
   */
  @Override
  public ResponseEntity<GradeDto> createGrade(Grade grade) {
    return ResponseEntity.ok(gradeService.createGrade(grade));
  }

  /**
   * Обновляет существующий грейд в базе данных
   *
   * @param id Идентификатор грейда для обновления
   * @param grade Объект грейда с новыми данными
   * @return ResponseEntity с обновленным объектом GradeDto и статусом 200 (OK)
   */
  @Override
  public ResponseEntity<GradeDto> updateGrade(Long id, Grade grade) {
    return ResponseEntity.ok(gradeService.updateGrade(grade, id));
  }

  /**
   * Удаляет все грейды из базы данных
   *
   * @return ResponseEntity с пустым телом и статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteAllGrades() {
    gradeService.deleteAllGrades();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет грейд по указанному идентификатору
   *
   * @param id Идентификатор грейда для удаления
   * @return ResponseEntity с пустым телом и статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteGradeById(Long id) {
    gradeService.deleteGradeById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает список всех грейдов из базы данных
   *
   * @return ResponseEntity со списком GradeDto и статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<GradeDto>> getAllGrades() {
    return ResponseEntity.ok(gradeService.getAllGrades());
  }

  /**
   * Возвращает грейд по указанному идентификатору
   *
   * @param id Идентификатор грейда
   * @return ResponseEntity с объектом GradeDto и статусом 200 (OK)
   */
  @Override
  public ResponseEntity<GradeDto> getGradeById(Long id) {
    return ResponseEntity.ok(gradeService.getGradeById(id));
  }
}