package com.anybank.controller;

import com.anybank.service.SalariesDataService;
import com.egorov.api.SalariesDataApi;
import com.egorov.model.SalariesData;
import com.egorov.model.SalariesDataDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с данными о зарплатах.
 * Обеспечивает REST API для выполнения CRUD операций с данными о зарплатах.
 * Реализует интерфейс {@link com.egorov.api.SalariesDataApi}.
 */
@RestController
@RequiredArgsConstructor
public class SalariesDataController implements SalariesDataApi {

  private final SalariesDataService salariesDataService;

  /**
   * Добавляет новые данные о зарплате в базу данных.
   *
   * @param salariesData Данные о зарплате для добавления
   * @return ResponseEntity с DTO добавленных данных о зарплате и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<SalariesDataDto> addSalariesData(SalariesData salariesData) {
    return ResponseEntity.ok(salariesDataService.addSalariesData(salariesData));
  }

  /**
   * Обновляет существующие данные о зарплате по указанному ID.
   *
   * @param id ID данных о зарплате для обновления
   * @param salariesData Новые данные о зарплате
   * @return ResponseEntity с DTO обновленных данных о зарплате и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<SalariesDataDto> updateSalariesDataById(Long id,
      SalariesData salariesData) {
    return ResponseEntity.ok(salariesDataService.updateSalariesData(salariesData, id));
  }

  /**
   * Удаляет все данные о зарплатах из базы данных.
   *
   * @return ResponseEntity с HTTP статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteAllSalariesData() {
    salariesDataService.deleteSalariesData();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет данные о зарплате по указанному ID.
   *
   * @param id ID данных о зарплате для удаления
   * @return ResponseEntity с HTTP статусом 204 (NO_CONTENT)
   */
  @Override
  public ResponseEntity<Void> deleteSalariesDataById(Long id) {
    salariesDataService.deleteSalariesDataById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Получает все данные о зарплатах из базы данных.
   *
   * @return ResponseEntity со списком DTO данных о зарплатах и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<List<SalariesDataDto>> getAllSalariesData() {
    return ResponseEntity.ok(salariesDataService.getSalariesData());
  }

  /**
   * Получает данные о зарплате по указанному ID.
   *
   * @param id ID данных о зарплате
   * @return ResponseEntity с DTO данных о зарплате и HTTP статусом 200 (OK)
   */
  @Override
  public ResponseEntity<SalariesDataDto> getSalariesDataById(Long id) {
    return ResponseEntity.ok(salariesDataService.getSalariesDataById(id));
  }
}