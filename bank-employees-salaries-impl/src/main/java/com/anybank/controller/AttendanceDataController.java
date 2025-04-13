package com.anybank.controller;

import com.anybank.service.AttendanceDataService;
import com.egorov.api.AttendanceDataApi;
import com.egorov.model.AttendanceData;
import com.egorov.model.AttendanceDataDto;
import com.egorov.model.DateTimePeriod;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с данными о посещаемости сотрудников.
 * Обеспечивает REST API для создания, получения, обновления и удаления данных о посещаемости.
 * Реализует интерфейс {@link com.egorov.api.AttendanceDataApi}.
 */
@RestController
@RequiredArgsConstructor
public class AttendanceDataController implements AttendanceDataApi {

  private final AttendanceDataService attendanceDataService;

  /**
   * Создает новую запись о посещаемости сотрудника за день
   *
   * @param data Данные о посещаемости для создания
   * @return DTO созданной записи о посещаемости с HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<AttendanceDataDto> createAttendanceData(AttendanceData data) {
    return ResponseEntity.ok(attendanceDataService.createAttendanceData(data));
  }

  /**
   * Удаляет все записи о посещаемости
   *
   * @return HTTP статус 204 (NO_CONTENT) в случае успешного удаления
   */
  @Override
  public ResponseEntity<Void> deleteAllAttendanceData() {
    attendanceDataService.deleteAllAttendanceData();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет запись о посещаемости по идентификатору
   *
   * @param id Идентификатор записи о посещаемости
   * @return HTTP статус 204 (NO_CONTENT) в случае успешного удаления
   */
  @Override
  public ResponseEntity<Void> deleteAttendanceDataById(Long id) {
    attendanceDataService.deleteAttendanceDataById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет записи о посещаемости за указанный период
   *
   * @param period Период времени, за который нужно удалить записи
   * @return HTTP статус 204 (NO_CONTENT) в случае успешного удаления
   */
  @Override
  public ResponseEntity<Void> deleteAttendanceDataByPeriod(DateTimePeriod period) {
    attendanceDataService.deleteAttendanceDataByPeriod(period);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет записи о посещаемости конкретного сотрудника за указанный период
   *
   * @param employeeId Идентификатор сотрудника
   * @param period Период времени, за который нужно удалить записи
   * @return HTTP статус 204 (NO_CONTENT) в случае успешного удаления
   */
  @Override
  public ResponseEntity<Void> deleteEmployeeAttendanceDataByPeriod(Long employeeId,
      DateTimePeriod period) {
    attendanceDataService.deleteEmployeeAttendanceDataByPeriod(employeeId, period);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает список записей о посещаемости сотрудников департамента за указанный период
   *
   * @param departmentId Идентификатор департамента
   * @param period Период времени, за который нужно получить записи
   * @return Список DTO записей о посещаемости с HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getDepartmentAttendanceDataByPeriod(
      Long departmentId, DateTimePeriod period) {
    return ResponseEntity.ok(
        attendanceDataService.getDepartmentAttendanceDataByPeriod(departmentId, period));
  }

  /**
   * Возвращает список всех записей о посещаемости за указанный период
   *
   * @param period Период времени, за который нужно получить записи
   * @return Список DTO записей о посещаемости с HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByPeriod(DateTimePeriod period) {
    return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriod(period));
  }

  /**
   * Возвращает список записей о посещаемости конкретного сотрудника за указанный период
   *
   * @param employeeId Идентификатор сотрудника
   * @param period Период времени, за который нужно получить записи
   * @return Список DTO записей о посещаемости с HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getEmployeeAttendanceDataByPeriod(
      Long employeeId, DateTimePeriod period) {
    return ResponseEntity.ok(
        attendanceDataService.getEmployeeAttendanceDataByPeriod(employeeId, period));
  }

  /**
   * Обновляет существующую запись о посещаемости
   *
   * @param id Идентификатор записи о посещаемости для обновления
   * @param attendanceData Новые данные о посещаемости
   * @return Обновленное DTO записи о посещаемости с HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<AttendanceDataDto> updateAttendanceData(Long id,
      AttendanceData attendanceData) {
    return ResponseEntity.ok(attendanceDataService.updateAttendanceData(attendanceData, id));
  }
}