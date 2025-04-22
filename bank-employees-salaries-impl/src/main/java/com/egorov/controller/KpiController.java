package com.egorov.controller;

import com.egorov.service.KpiService;
import com.egorov.api.KpiApi;
import com.egorov.model.Kpi;
import com.egorov.model.KpiDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с KPI (Key Performance Indicators).
 * Обеспечивает REST API для добавления, обновления, удаления и получения KPI.
 * Реализует интерфейс {@link com.egorov.api.KpiApi}.
 */
@RestController
@RequiredArgsConstructor
public class KpiController implements KpiApi {

  private final KpiService kpiService;

  /**
   * Добавляет новый KPI в систему
   *
   * @param kpi Объект KPI для добавления
   * @return ResponseEntity с созданным KPI в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<KpiDto> addKpi(Kpi kpi) {
    return ResponseEntity.ok(kpiService.addKpi(kpi));
  }

  /**
   * Обновляет существующий KPI по идентификатору
   *
   * @param id Идентификатор KPI для обновления
   * @param kpi Объект KPI с новыми данными
   * @return ResponseEntity с обновленным KPI в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<KpiDto> updateKpi(Long id, Kpi kpi) {
    return ResponseEntity.ok(kpiService.updateKpi(kpi, id));
  }

  /**
   * Удаляет все KPI из системы
   *
   * @return ResponseEntity с пустым телом и статусом 204 NO_CONTENT
   */
  @Override
  public ResponseEntity<Void> deleteKpis() {
    kpiService.deleteKpis();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Удаляет KPI по идентификатору
   *
   * @param id Идентификатор KPI для удаления
   * @return ResponseEntity с пустым телом и статусом 204 NO_CONTENT
   */
  @Override
  public ResponseEntity<Void> deleteKpiById(Long id) {
    kpiService.deleteKpiById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Возвращает список всех KPI в системе
   *
   * @return ResponseEntity со списком KPI в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<List<KpiDto>> getKpis() {
    return ResponseEntity.ok(kpiService.getKpis());
  }

  /**
   * Возвращает KPI по идентификатору
   *
   * @param id Идентификатор KPI
   * @return ResponseEntity с KPI в формате DTO и статусом 200 OK
   */
  @Override
  public ResponseEntity<KpiDto> getKpiById(Long id) {
    return ResponseEntity.ok(kpiService.getKpiById(id));
  }
}