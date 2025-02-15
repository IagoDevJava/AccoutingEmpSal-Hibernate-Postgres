package com.anybank.controller;

import com.anybank.dto.GradeDto;
import com.anybank.model.Grade;
import com.anybank.service.GradeService;
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
@RequestMapping("/grades")
public class GradeController {
    private final GradeService gradeService;

    /**
     * Добавление грейда в БД
     */
    @Transactional
    @PostMapping
    public ResponseEntity<GradeDto> addGrade(@Valid @RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.addGrade(grade));
    }

    /**
     * Обновление грейда в БД
     */
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<GradeDto> updateGrade(@Valid @RequestBody Grade grade,
                                                @PathVariable @PositiveOrZero Integer id) {
        return ResponseEntity.ok(gradeService.updateGrade(grade, id));
    }

    /**
     * Удаление всех грейдов из БД
     */
    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteGrades() {
        gradeService.deleteGrades();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Удаление грейда по id из БД
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGradeById(@PositiveOrZero @PathVariable Integer id) {
        gradeService.deleteGradeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Получение списка грейдов из БД
     */
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<GradeDto>> getGrades() {
        return ResponseEntity.ok(gradeService.getGrades());
    }

    /**
     * Получение грейда по id
     */
    @Transactional(readOnly = true)
    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PositiveOrZero @PathVariable Integer id) {
        return ResponseEntity.ok(gradeService.getGradeById(id));
    }
}
