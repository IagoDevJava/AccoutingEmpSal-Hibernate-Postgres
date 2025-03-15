package com.anybank.controller;

import com.anybank.dto.WorkScheduleDto;
import com.anybank.model.WorkSchedule;
import com.anybank.service.WorkScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
@RequestMapping("/work-schedules")
public class WorkScheduleController{
    private final WorkScheduleService workScheduleService;

    /**
     * Добавить график в БД
     */
    @Transactional
    @PostMapping()
    public ResponseEntity<WorkScheduleDto> addSchedule(@Valid @RequestBody WorkSchedule workSchedule) {
        return ResponseEntity.ok(workScheduleService.addSchedule(workSchedule));
    }

    /**
     * Изменить график в БД
     */
    @Transactional
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<WorkScheduleDto> updateSchedule(@RequestBody WorkSchedule workSchedule,
                                                          @PositiveOrZero @PathVariable Integer scheduleId) {
        return ResponseEntity.ok(workScheduleService.updateSchedule(workSchedule, scheduleId));
    }

    /**
     * Удалить графики из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteSchedules() {
        workScheduleService.deleteSchedules();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удалить график в БД по id
     */
    @Transactional
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteScheduleById(@PositiveOrZero @PathVariable Integer scheduleId) {
        workScheduleService.deleteScheduleById(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получить все графики в БД
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<WorkScheduleDto>> getSchedules() {
        return ResponseEntity.ok(workScheduleService.getSchedules());
    }

    /**
     * Получить график в БД по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{scheduleId}")
    public ResponseEntity<WorkScheduleDto> getScheduleById(@PositiveOrZero @PathVariable Integer scheduleId) {
        return ResponseEntity.ok(workScheduleService.getScheduleById(scheduleId));
    }
}
