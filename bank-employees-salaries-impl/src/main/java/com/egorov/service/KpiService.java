package com.egorov.service;


import com.egorov.model.Kpi;
import com.egorov.model.KpiDto;
import java.util.List;

public interface KpiService {

  /**
   * Добавление kpi в БД
   */
  KpiDto addKpi(Kpi kpi);

  /**
   * Обновление kpi в БД
   */
  KpiDto updateKpi(Kpi kpi, Long id);

  /**
   * Удаление всех kpi из БД
   */
  void deleteKpis();

  /**
   * Удаление kpi по id из БД
   */
  void deleteKpiById(Long id);

  /**
   * Получение списка kpi из БД
   */
  List<KpiDto> getKpis();

  /**
   * Получение kpi по id
   */
  KpiDto getKpiById(Long id);
}
