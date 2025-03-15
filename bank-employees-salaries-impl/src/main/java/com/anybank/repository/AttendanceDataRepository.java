package com.anybank.repository;

import com.anybank.api.model.AttendanceData;
import com.anybank.api.model.AttendanceDataDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.threeten.bp.OffsetDateTime;

public interface AttendanceDataRepository extends JpaRepository<AttendanceData, Long> {

  void deleteBetweenBeginAndEnd(OffsetDateTime begin, OffsetDateTime end);

  void deleteByEmployeeBetweenBeginAndEnd(Long employeeId, OffsetDateTime begin,
      OffsetDateTime end);

  @Query(value = """
      SELECT at.id, at.date_att, at.employee_id, at.status
      FROM attendance_data AS at
               LEFT JOIN employees e on e.id = at.employee_id
      WHERE e.department_id = ?
        AND (date_att between ? AND ?)""", nativeQuery = true)
  List<AttendanceDataDto> findAttendanceDataByDepartmentByPeriod(Long departmentId,
      OffsetDateTime begin, OffsetDateTime end);

  @Query(value = """
      SELECT at.id, at.date_att, at.employee_id, at.status
      FROM attendance_data AS at
      WHERE (date_att between ? AND ?)""", nativeQuery = true)
  List<AttendanceDataDto> findAttendanceDataByPeriod(OffsetDateTime begin, OffsetDateTime end);

  @Query(value = """
      SELECT at.id, at.date_att, at.employee_id, at.status
      FROM attendance_data AS at
               LEFT JOIN employees e on e.id = at.employee_id
      WHERE e.employee_id = ?
        AND (date_att between ? AND ?)""", nativeQuery = true)
  List<AttendanceDataDto> findAttendanceDataByEmployeeByPeriod(Long employeeId,
      OffsetDateTime begin, OffsetDateTime end);
}