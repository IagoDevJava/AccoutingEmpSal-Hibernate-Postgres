package com.anybank.mapper;

import com.anybank.api.model.AttendanceData;
import com.anybank.api.model.AttendanceDataDto;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDataMapper {
    //AttendanceData to AttendanceDataDto
    public static AttendanceDataDto toAttendanceDataDto(AttendanceData attendanceData) {
        return new AttendanceDataDto()
                .id(attendanceData.getId())
                .dateAtt(attendanceData.getDateAtt())
                .employeeId(attendanceData.getEmployee().getId())
                .status(attendanceData.getStatus());
    }

    //AttendanceDataList to AttendanceDataDtoList
    public static List<AttendanceDataDto> toAttendanceDataDtoList(List<AttendanceData> attendanceData) {
        List<AttendanceDataDto> result = new ArrayList<>();
        for (AttendanceData attendanceData1 : attendanceData) {
            result.add(toAttendanceDataDto(attendanceData1));
        }
        return result;
    }
}
