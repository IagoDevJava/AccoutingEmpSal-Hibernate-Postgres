package com.egorov.mapper;

import com.egorov.model.AttendanceData;
import com.egorov.model.AttendanceDataDto;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDataMapper {

  //AttendanceData to AttendanceDataDto
  public static AttendanceDataDto toAttendanceDataDto(AttendanceData attendanceData) {
    return new AttendanceDataDto()
        .id(attendanceData.getId())
        .dateAtt(attendanceData.getDateAtt())
        .employeeId(attendanceData.getEmployeeId())
        .status(attendanceData.getStatus());
  }

  //AttendanceDataList to AttendanceDataDtoList
  public static List<AttendanceDataDto> toAttendanceDataDtoList(
      List<AttendanceData> attendanceData) {
    List<AttendanceDataDto> result = new ArrayList<>();
    for (AttendanceData attendanceData1 : attendanceData) {
      result.add(toAttendanceDataDto(attendanceData1));
    }
    return result;
  }
}
