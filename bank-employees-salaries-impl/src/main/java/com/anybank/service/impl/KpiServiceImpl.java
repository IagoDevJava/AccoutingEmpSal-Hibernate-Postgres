package com.anybank.service.impl;

import com.anybank.exception.KpiNotFoundException;
import com.anybank.mapper.KpiMapper;
import com.anybank.repository.KpiRepository;
import com.anybank.service.KpiService;
import com.egorov.model.Kpi;
import com.egorov.model.KpiDto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KpiServiceImpl implements KpiService {

  private final KpiRepository kpiRepository;

  @Override
  public KpiDto addKpi(Kpi kpi) {
    return KpiMapper.toKpiDto(kpiRepository.save(kpi));
  }

  @Override
  public KpiDto updateKpi(Kpi kpi, Long id) {
    Kpi kpiById = kpiRepository.findById(id)
        .orElseThrow(() -> new KpiNotFoundException("Kpi not found"));

    kpiById.setId(id);
    kpiById.setEmployee(kpi.getEmployee());
    kpiById.setPersonalKpi(kpi.getPersonalKpi());
    kpiById.setTeamKpi(kpi.getTeamKpi());
    kpiById.setCommonKpi(kpi.getCommonKpi());
    kpiById.setMonth(kpi.getMonth());
    kpiById.setYear(kpi.getYear());

    return KpiMapper.toKpiDto(kpiRepository.save(kpiById));
  }

  @Override
  public void deleteKpis() {
    kpiRepository.deleteAll();
  }

  /**
   * Удаление kpi по id из БД
   */
  @Override
  public void deleteKpiById(Long id) {
    kpiRepository.findById(id).orElseThrow(() -> new KpiNotFoundException("Kpi not found"));
    kpiRepository.deleteById(id);
  }

  /**
   * Получение списка kpi из БД
   */
  @Override
  public List<KpiDto> getKpis() {
    return KpiMapper.toKpiDtoList(kpiRepository.findAll());
  }

  /**
   * Получение kpi по id
   */
  @Override
  public KpiDto getKpiById(Long id) {
    return KpiMapper.toKpiDto(kpiRepository.findById(id)
        .orElseThrow(() -> new KpiNotFoundException("Kpi not found")));
  }
}
