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

@RestController
@RequiredArgsConstructor
public class AttendanceDataController implements AttendanceDataApi {

  private final AttendanceDataService attendanceDataService;

  /**
   * Ввести данные за день
   */
  @Override
  public ResponseEntity<AttendanceDataDto> createAttendanceData(AttendanceData data) {
    return ResponseEntity.ok(attendanceDataService.createAttendanceData(data));
  }

  /**
   * Удалить все данные
   */
  @Override
  public ResponseEntity<Void> deleteAllAttendanceData() {
    attendanceDataService.deleteAllAttendanceData();
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
  public ResponseEntity<Void> deleteEmployeeAttendanceDataByPeriod(Long employeeId,
      DateTimePeriod period) {
    attendanceDataService.deleteEmployeeAttendanceDataByPeriod(employeeId, period);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * получить данные по департаменту за период
   */
  @Override
  public ResponseEntity<List<AttendanceDataDto>> getDepartmentAttendanceDataByPeriod(
      Long departmentId, DateTimePeriod period) {
    return ResponseEntity.ok(
        attendanceDataService.getDepartmentAttendanceDataByPeriod(departmentId, period));
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
  public ResponseEntity<List<AttendanceDataDto>> getEmployeeAttendanceDataByPeriod(
      Long employeeId, DateTimePeriod period) {
    return ResponseEntity.ok(
        attendanceDataService.getEmployeeAttendanceDataByPeriod(employeeId, period));
  }

  /**
   * Изменить данные за день
   */
  @Override
  public ResponseEntity<AttendanceDataDto> updateAttendanceData(Long id,
      AttendanceData attendanceData) {
    return ResponseEntity.ok(attendanceDataService.updateAttendanceData(attendanceData, id));
  }
}
