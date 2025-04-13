package com.anybank.service;


import com.egorov.model.AttendanceData;
import com.egorov.model.AttendanceDataDto;
import com.egorov.model.DateTimePeriod;
import java.util.List;

public interface AttendanceDataService {

  /**
   * Ввести данные за день
   */
  AttendanceDataDto createAttendanceData(AttendanceData attendanceData);

  /**
   * Изменить данные за день
   */
  AttendanceDataDto updateAttendanceData(AttendanceData attendanceData, Long id);

  /**
   * Удалить все данные
   */
  void deleteAllAttendanceData();

  /**
   * получить данные за период
   */
  List<AttendanceDataDto> getAttendanceDataByPeriod(DateTimePeriod period);

  /**
   * получить данные по сотруднику за период
   */
  List<AttendanceDataDto> getEmployeeAttendanceDataByPeriod(Long employeeId,
      DateTimePeriod period);

  /**
   * Удалить данные по идентификатору
   */
  void deleteAttendanceDataById(Long id);

  /**
   * Удалить данные за период
   */
  void deleteAttendanceDataByPeriod(DateTimePeriod body);

  /**
   * Удалить данные за период по сотруднику
   */
  void deleteEmployeeAttendanceDataByPeriod(Long employeeId, DateTimePeriod body);

  /**
   * получить данные по департаменту за период
   */
  List<AttendanceDataDto> getDepartmentAttendanceDataByPeriod(Long departmentId,
      DateTimePeriod body);
}
