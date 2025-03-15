package com.anybank.controller;

import com.anybank.api.AttendanceDataApi;

import com.anybank.api.model.AttendanceData;
import com.anybank.api.model.AttendanceDataDto;
import com.anybank.service.AttendanceDataService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
@RequestMapping("/attendance-data")
public class AttendanceDataController implements AttendanceDataApi {
    private final AttendanceDataService attendanceDataService;

    /**
     * Ввести данные за день
     */
    @Transactional
    @Override
    public ResponseEntity<AttendanceDataDto> addAttendanceData(AttendanceData attendanceData) {
        return ResponseEntity.ok(attendanceDataService.addAttendanceData(attendanceData));
    }

    /**
     * Изменить данные за день
     */
    @Transactional
    @Override
    public ResponseEntity<AttendanceDataDto> updateAttendanceData(Long id, AttendanceData attendanceData) {
        return ResponseEntity.ok(attendanceDataService.updateAttendanceData(attendanceData, id));
    }

    /**
     * Удалить данные за день
     */
    @Transactional
    @DeleteMapping("/{date}")
    public ResponseEntity<Void> deleteAttendanceDataByDay(@DateTimeFormat @PathVariable String date) {
        attendanceDataService.deleteAttendanceDataByDay(date);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить все данные
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteAttendanceData() {
        attendanceDataService.deleteAttendanceData();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить данные по сотруднику
     */
    @Transactional
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteAttendanceDataByEmployee(@PositiveOrZero @PathVariable Integer employeeId) {
        attendanceDataService.deleteAttendanceDataByEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * получить данные по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDataDto> getAttendanceDataById(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataById(id));
    }

    /**
     * получить данные за период
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByPeriod(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriod(
                Objects.requireNonNullElse(start, String.valueOf(LocalDate.now().minusYears(1))),
                Objects.requireNonNullElse(end, String.valueOf(LocalDate.now())))
        );
    }

    /**
     * получить данные по сотруднику за период
     */
    @Transactional(readOnly = true)
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByPeriodByEmployee(
            @PositiveOrZero @PathVariable Integer employeeId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriodByEmployee(
                employeeId,
                Objects.requireNonNullElse(start, String.valueOf(LocalDate.now().minusYears(1))),
                Objects.requireNonNullElse(end, String.valueOf(LocalDate.now())))
        );
    }

    /**
     * получить данные по отделу за период
     */
    @Transactional(readOnly = true)
    @GetMapping("/{departmentId}")
    public ResponseEntity<List<AttendanceDataDto>> getAttendanceDataByPeriodByDepartment(
            @PositiveOrZero @PathVariable Integer departmentId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        return ResponseEntity.ok(attendanceDataService.getAttendanceDataByPeriodByDepartment(
                departmentId,
                Objects.requireNonNullElse(start, String.valueOf(LocalDate.now().minusYears(1))),
                Objects.requireNonNullElse(end, String.valueOf(LocalDate.now())))
        );
    }
}
