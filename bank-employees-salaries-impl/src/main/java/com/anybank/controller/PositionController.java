package com.anybank.controller;

import com.anybank.service.PositionService;
import com.egorov.api.PositionsApi;
import com.egorov.model.Position;
import com.egorov.model.PositionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для управления должностями в системе.
 * Обеспечивает REST API для выполнения CRUD операций с должностями.
 * Реализует интерфейс {@link PositionsApi}.
 *
 * <p>Все методы возвращают {@link ResponseEntity} с соответствующим HTTP статусом
 * и телом ответа, содержащим результат операции или данные.</p>
 *
 * @see PositionsApi
 * @see PositionService
 * @see Position
 * @see PositionDto
 */
@RestController
@RequiredArgsConstructor
public class PositionController implements PositionsApi {

  private final PositionService positionService;

  /**
   * Добавляет новую должность в систему.
   *
   * @param position Объект должности для добавления. Должен быть валидным.
   * @return {@link ResponseEntity} с HTTP статусом 200 (OK) и добавленной должностью
   *         в формате {@link PositionDto}
   */
  @Override
  public ResponseEntity<PositionDto> addPosition(@Valid @RequestBody Position position) {
    return ResponseEntity.ok(positionService.addPosition(position));
  }

  /**
   * Обновляет существующую должность по её идентификатору.
   *
   * @param id Идентификатор должности для обновления. Должен быть положительным или нулём.
   * @param position Объект должности с новыми данными.
   * @return {@link ResponseEntity} с HTTP статусом 200 (OK) и обновлённой должностью
   *         в формате {@link PositionDto}
   */
  @Override
  public ResponseEntity<PositionDto> updatePosition(Long id, Position position) {
    return ResponseEntity.ok(positionService.updatePosition(position, id));
  }

  /**
   * Удаляет все должности из системы.
   *
   * @return {@link ResponseEntity} с HTTP статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteAllPositions() {
    positionService.deletePositions();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет должность по её идентификатору.
   *
   * @param id Идентификатор должности для удаления. Должен быть положительным или нулём.
   * @return {@link ResponseEntity} с HTTP статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deletePositionById(@PositiveOrZero @PathVariable Long id) {
    positionService.deletePositionById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает список всех должностей в системе.
   *
   * @return {@link ResponseEntity} с HTTP статусом 200 (OK) и списком должностей
   *         в формате {@link PositionDto}
   */
  @Override
  public ResponseEntity<List<PositionDto>> getPositions() {
    return ResponseEntity.ok(positionService.getPosition());
  }

  /**
   * Возвращает должность по её идентификатору.
   *
   * @param id Идентификатор должности. Должен быть положительным или нулём.
   * @return {@link ResponseEntity} с HTTP статусом 200 (OK) и найденной должностью
   *         в формате {@link PositionDto}
   */
  @Override
  public ResponseEntity<PositionDto> getPositionById(@PositiveOrZero @PathVariable Long id) {
    return ResponseEntity.ok(positionService.getPositionById(id));
  }
}