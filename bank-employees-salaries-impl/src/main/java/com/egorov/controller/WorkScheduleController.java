package com.egorov.controller;

import com.egorov.service.WorkScheduleService;
import com.egorov.api.WorkScheduleApi;
import com.egorov.model.WorkSchedule;
import com.egorov.model.WorkScheduleDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления графиками работы. Обеспечивает REST API для выполнения операций CRUD с
 * графиками работы. Реализует интерфейс {@link WorkScheduleApi}.
 */
@RestController
@RequiredArgsConstructor
public class WorkScheduleController implements WorkScheduleApi {

  private final WorkScheduleService workScheduleService;

  /**
   * Добавляет новый график работы
   *
   * @param workSchedule график работы для добавления
   * @return ResponseEntity с добавленным графиком работы в формате DTO и статусом OK
   */
  @Override
  public ResponseEntity<WorkScheduleDto> addSchedule(WorkSchedule workSchedule) {
    return ResponseEntity.ok(workScheduleService.addSchedule(workSchedule));
  }

  /**
   * Обновляет существующий график работы
   *
   * @param scheduleId   идентификатор графика работы для обновления
   * @param workSchedule новые данные графика работы
   * @return ResponseEntity с обновленным графиком работы в формате DTO и статусом OK
   */
  @Override
  public ResponseEntity<WorkScheduleDto> updateSchedule(Integer scheduleId,
      WorkSchedule workSchedule) {
    return ResponseEntity.ok(workScheduleService.updateSchedule(workSchedule, scheduleId));
  }

  /**
   * Удаляет все графики работы
   *
   * @return ResponseEntity с пустым телом и статусом NO_CONTENT
   */
  @Override
  public ResponseEntity<Void> deleteSchedules() {
    workScheduleService.deleteSchedules();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет график работы по идентификатору
   *
   * @param scheduleId идентификатор графика работы для удаления
   * @return ResponseEntity с пустым телом и статусом NO_CONTENT
   */
  @Override
  public ResponseEntity<Void> deleteScheduleById(Integer scheduleId) {
    workScheduleService.deleteScheduleById(scheduleId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает список всех графиков работы
   *
   * @return ResponseEntity со списком графиков работы в формате DTO и статусом OK
   */
  @Override
  public ResponseEntity<List<WorkScheduleDto>> getSchedules() {
    return ResponseEntity.ok(workScheduleService.getSchedules());
  }

  /**
   * Возвращает график работы по идентификатору
   *
   * @param scheduleId идентификатор графика работы
   * @return ResponseEntity с графиком работы в формате DTO и статусом OK
   */
  @Override
  public ResponseEntity<WorkScheduleDto> getScheduleById(Integer scheduleId) {
    return ResponseEntity.ok(workScheduleService.getScheduleById(scheduleId));
  }
}
