package com.anybank.controller;

import com.anybank.dto.PositionDto;
import com.anybank.model.Position;
import com.anybank.service.PositionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(isolation = Isolation.READ_COMMITTED)
@AllArgsConstructor
@RequestMapping("/positions")
public class PositionController {

  private final PositionService positionService;

  /**
   * Добавление должности в БД
   */
  @Transactional
  @PostMapping
  public ResponseEntity<PositionDto> addPosition(@Valid @RequestBody Position position) {
    return ResponseEntity.ok(positionService.addPosition(position));
  }

  /**
   * Обновление должности в БД
   */
  @Transactional
  @PatchMapping("/{id}")
  public ResponseEntity<PositionDto> updatePosition(@RequestBody Position position,
      @PathVariable @PositiveOrZero Long id) {
    return ResponseEntity.ok(positionService.updatePosition(position, id));
  }

  /**
   * Удаление всех должностей из БД
   */
  @Transactional
  @DeleteMapping
  public ResponseEntity<Void> deletePositions() {
    positionService.deletePositions();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаление должности по id из БД
   */
  @Transactional
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePositionById(@PositiveOrZero @PathVariable Long id) {
    positionService.deletePositionById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Получение списка должностей из БД
   */
  @Transactional(readOnly = true)
  @GetMapping
  public ResponseEntity<List<PositionDto>> getPosition() {
    return ResponseEntity.ok(positionService.getPosition());
  }

  /**
   * Получение должности по id
   */
  @Transactional(readOnly = true)
  @GetMapping("/{id}")
  public ResponseEntity<PositionDto> getPositionById(@PositiveOrZero @PathVariable Long id) {
    return ResponseEntity.ok(positionService.getPositionById(id));
  }
}
