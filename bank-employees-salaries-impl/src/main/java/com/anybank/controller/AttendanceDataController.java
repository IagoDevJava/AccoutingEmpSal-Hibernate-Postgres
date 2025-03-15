package com.anybank.controller;

import com.anybank.api.AttendanceDataApi;
import com.anybank.api.model.AttendanceData;
import com.anybank.api.model.AttendanceDataDto;
import com.anybank.api.model.DateTimePeriod;
import com.anybank.service.AttendanceDataService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AttendanceDataController implements AttendanceDataApi {

  private final AttendanceDataService attendanceDataService;

  /**
   * Ввести данные за день
   */
  @Override
  public ResponseEntity<AttendanceDataDto> addAttendanceData(AttendanceData data) {
    return ResponseEntity.ok(attendanceDataService.addAttendanceData(data));
  }

  /**
   * Удалить все данные
   */
  @Override
  public ResponseEntity<Void> deleteAttendanceData() {
    attendanceDataService.deleteAttendanceData();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удалить данные по идентификатору
   */
  @Override
  public ResponseEntity<Void> deleteAttendanceDataById(Long id) {
    attendanceDataService.deleteAttendanceDataById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удалить данные за период
   */
  @Override
  public ResponseEntity<Void> deleteAttendanceDataByPeriod(DateTimePeriod period) {
    attendanceDataService.deleteAttendanceDataByPeriod(period);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удалить данные за период по сотруднику
   */
  @Override
  public ResponseEntity<Void> deleteAttendanceDataByPeriodByEmployee(Long employeeId,
      DateTimePeriod period) {
    attendanceDataService.deleteAttendanceDataByPeriodByEmployee(employeeId, period);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * получить данные по департаменту за период
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByDepartmentByPeriod(
      Long departmentId, DateTimePeriod period) {
    return ResponseEntity.ok(
        attendanceDataService.getAttendanceDataByDepartmentByPeriod(departmentId, period));
  }

  /**
   * получить данные за период
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByPeriod(DateTimePeriod period) {
    return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriod(period));
  }

  /**
   * получить данные по сотруднику за период
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByPeriodByEmployee(
      Long employeeId, DateTimePeriod period) {
    return ResponseEntity.ok(
        attendanceDataService.getAttendanceDataByPeriodByEmployee(employeeId, period));
  }

  /**
   * Изменить данные за день
   */
  @Transactional
  @Override
  public ResponseEntity<AttendanceDataDto> updateAttendanceData(Long id,
      AttendanceData attendanceData) {
    return ResponseEntity.ok(attendanceDataService.updateAttendanceData(attendanceData, id));
  }
}
