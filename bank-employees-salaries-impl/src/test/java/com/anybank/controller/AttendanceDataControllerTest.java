package com.anybank.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.anybank.service.AttendanceDataService;
import com.egorov.model.AttendanceData;
import com.egorov.model.AttendanceDataDto;
import com.egorov.model.DateTimePeriod;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AttendanceDataControllerTest {

  @Mock
  private AttendanceDataService attendanceDataService;

  @InjectMocks
  private AttendanceDataController attendanceDataController;

  private AttendanceData attendanceData;
  private AttendanceDataDto attendanceDataDto;
  private DateTimePeriod period;
  private final Long testId = 1L;
  private final Long employeeId = 1L;

  @BeforeEach
  void setUp() {
    attendanceData = new AttendanceData();
    attendanceDataDto = new AttendanceDataDto();
    period = new DateTimePeriod(OffsetDateTime.now().minusHours(1L),
        OffsetDateTime.now().plusHours(1L));
  }

  @Test
  void createAttendanceData_shouldReturnOkWithDto() {
    when(attendanceDataService.createAttendanceData(any(AttendanceData.class)))
        .thenReturn(attendanceDataDto);

    ResponseEntity<AttendanceDataDto> response =
        attendanceDataController.createAttendanceData(attendanceData);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(attendanceDataDto, response.getBody());
    verify(attendanceDataService).createAttendanceData(attendanceData);
  }

  @Test
  void deleteAllAttendanceData_shouldReturnNoContent() {
    ResponseEntity<Void> response = attendanceDataController.deleteAllAttendanceData();

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(attendanceDataService).deleteAllAttendanceData();
  }

  @Test
  void deleteAttendanceDataById_shouldReturnNoContent() {
    ResponseEntity<Void> response = attendanceDataController.deleteAttendanceDataById(testId);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(attendanceDataService).deleteAttendanceDataById(testId);
  }

  @Test
  void deleteAttendanceDataByPeriod_shouldReturnNoContent() {
    ResponseEntity<Void> response =
        attendanceDataController.deleteAttendanceDataByPeriod(period);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(attendanceDataService).deleteAttendanceDataByPeriod(period);
  }

  @Test
  void deleteEmployeeAttendanceDataByPeriod_shouldReturnNoContent() {
    ResponseEntity<Void> response =
        attendanceDataController.deleteEmployeeAttendanceDataByPeriod(employeeId, period);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(attendanceDataService).deleteEmployeeAttendanceDataByPeriod(employeeId, period);
  }

  @Test
  void getDepartmentAttendanceDataByPeriod_shouldReturnOkWithList() {
    List<AttendanceDataDto> expectedList = Collections.singletonList(attendanceDataDto);
    when(attendanceDataService.getDepartmentAttendanceDataByPeriod(anyLong(),
        any(DateTimePeriod.class)))
        .thenReturn(expectedList);

    Long departmentId = 1L;
    ResponseEntity<List<AttendanceDataDto>> response =
        attendanceDataController.getDepartmentAttendanceDataByPeriod(departmentId, period);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedList, response.getBody());
    verify(attendanceDataService).getDepartmentAttendanceDataByPeriod(departmentId, period);
  }

  @Test
  void getAttendanceDataByPeriod_shouldReturnOkWithList() {
    List<AttendanceDataDto> expectedList = Collections.singletonList(attendanceDataDto);
    when(attendanceDataService.getAttendanceDataByPeriod(any(DateTimePeriod.class)))
        .thenReturn(expectedList);

    ResponseEntity<List<AttendanceDataDto>> response =
        attendanceDataController.getAttendanceDataByPeriod(period);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedList, response.getBody());
    verify(attendanceDataService).getAttendanceDataByPeriod(period);
  }

  @Test
  void getEmployeeAttendanceDataByPeriod_shouldReturnOkWithList() {
    List<AttendanceDataDto> expectedList = Collections.singletonList(attendanceDataDto);
    when(attendanceDataService.getEmployeeAttendanceDataByPeriod(anyLong(),
        any(DateTimePeriod.class)))
        .thenReturn(expectedList);

    ResponseEntity<List<AttendanceDataDto>> response =
        attendanceDataController.getEmployeeAttendanceDataByPeriod(employeeId, period);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedList, response.getBody());
    verify(attendanceDataService).getEmployeeAttendanceDataByPeriod(employeeId, period);
  }

  @Test
  void updateAttendanceData_shouldReturnOkWithDto() {
    when(attendanceDataService.updateAttendanceData(any(AttendanceData.class), anyLong()))
        .thenReturn(attendanceDataDto);

    ResponseEntity<AttendanceDataDto> response =
        attendanceDataController.updateAttendanceData(testId, attendanceData);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(attendanceDataDto, response.getBody());
    verify(attendanceDataService).updateAttendanceData(attendanceData, testId);
  }
}