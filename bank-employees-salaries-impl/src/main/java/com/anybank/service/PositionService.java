package com.anybank.service;


import com.anybank.dto.PositionDto;
import com.anybank.model.Position;
import java.util.List;

public interface PositionService {

  /**
   * Добавление должности в БД
   */
  PositionDto addPosition(Position position);

  /**
   * Обновление должности в БД
   */
  PositionDto updatePosition(Position position, Long id);

  /**
   * Удаление всех должностей из БД
   */
  void deletePositions();

  /**
   * Удаление должности по id из БД
   */
  void deletePositionById(Long id);

  /**
   * Получение списка должностей из БД
   */
  List<PositionDto> getPosition();

  /**
   * Получение должности по id
   */
  PositionDto getPositionById(Long id);
}
