package com.anybank.service.impl;

import com.anybank.exception.AttendanceDataNotFoundException;
import com.anybank.mapper.AttendanceDataMapper;
import com.anybank.repository.AttendanceDataRepository;
import com.anybank.service.AttendanceDataService;
import com.egorov.model.AttendanceData;
import com.egorov.model.AttendanceDataDto;
import com.egorov.model.DateTimePeriod;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceDataServiceImpl implements AttendanceDataService {

  private final AttendanceDataRepository attendanceDataRepository;

  @Override
  public AttendanceDataDto createAttendanceData(AttendanceData attendanceData) {
    return AttendanceDataMapper.toAttendanceDataDto(attendanceDataRepository.save(attendanceData));
  }

  @Override
  public void deleteAllAttendanceData() {
    attendanceDataRepository.deleteAll();
  }

  @Override
  public void deleteAttendanceDataById(Long id) {
    attendanceDataRepository.deleteById(id);
  }

  @Override
  public void deleteAttendanceDataByPeriod(DateTimePeriod period) {
    attendanceDataRepository.deleteBetweenBeginAndEnd(period.getBegin(), period.getEnd());
  }

  @Override
  public void deleteEmployeeAttendanceDataByPeriod(Long employeeId, DateTimePeriod period) {
    attendanceDataRepository.deleteByEmployeeBetweenBeginAndEnd(employeeId, period.getBegin(),
        period.getEnd());
  }

  @Override
  public List<AttendanceDataDto> getDepartmentAttendanceDataByPeriod(Long departmentId,
      DateTimePeriod period) {
    return attendanceDataRepository.findAttendanceDataByDepartmentByPeriod(departmentId,
        period.getBegin(), period.getEnd());
  }

  @Override
  public List<AttendanceDataDto> getAttendanceDataByPeriod(DateTimePeriod period) {
    return attendanceDataRepository.findAttendanceDataByPeriod(period.getBegin(), period.getEnd());
  }

  @Override
  public List<AttendanceDataDto> getEmployeeAttendanceDataByPeriod(Long employeeId,
      DateTimePeriod period) {
    return attendanceDataRepository.findAttendanceDataByEmployeeByPeriod(employeeId,
        period.getBegin(), period.getEnd());
  }

  @Override
  public AttendanceDataDto updateAttendanceData(AttendanceData attendanceData, Long id) {
    AttendanceData attendanceById = attendanceDataRepository.findById(id)
        .orElseThrow(() -> new AttendanceDataNotFoundException("Attendance not found"));

    attendanceById.setId(id);
    attendanceById.setDateAtt(attendanceData.getDateAtt());
    attendanceById.setEmployeeId(attendanceData.getEmployeeId());
    attendanceById.setStatus(attendanceData.getStatus());

    return AttendanceDataMapper.toAttendanceDataDto(attendanceDataRepository.save(attendanceById));
  }
}
