package com.anybank.service;

import com.anybank.api.model.AttendanceData;
import com.anybank.api.model.AttendanceDataDto;
import com.anybank.api.model.DateTimePeriod;

import java.util.List;

public interface AttendanceDataService {
    /**
     * Ввести данные за день
     */
    AttendanceDataDto addAttendanceData(AttendanceData attendanceData);

    /**
     * Изменить данные за день
     */
    AttendanceDataDto updateAttendanceData(AttendanceData attendanceData, Long id);

    /**
     * Удалить все данные
     */
    void deleteAttendanceData();

    /**
     * получить данные за период
     */
    List<AttendanceDataDto> getAttendanceDataByPeriod(DateTimePeriod period);

    /**
     * получить данные по сотруднику за период
     */
    List<AttendanceDataDto> getAttendanceDataByPeriodByEmployee(Long employeeId, DateTimePeriod period);

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
    void deleteAttendanceDataByPeriodByEmployee(Long employeeId, DateTimePeriod body);

    /**
     * получить данные по департаменту за период
     */
    List<AttendanceDataDto> getAttendanceDataByDepartmentByPeriod(Long departmentId, DateTimePeriod body);
}
