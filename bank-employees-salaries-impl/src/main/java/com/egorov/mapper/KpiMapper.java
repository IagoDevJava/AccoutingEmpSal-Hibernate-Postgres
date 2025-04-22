package com.egorov.mapper;

import com.egorov.model.Kpi;
import com.egorov.model.KpiDto;
import java.util.ArrayList;
import java.util.List;

public class KpiMapper {

  //Kpi to KpiDto
  public static KpiDto toKpiDto(Kpi kpi) {
    return new KpiDto(
        kpi.getPersonalKpi(),
        kpi.getTeamKpi(),
        kpi.getCommonKpi(),
        kpi.getEmployee().getId(),
        kpi.getMonth(),
        kpi.getYear())
        .id(kpi.getId());
  }

  //KpiList to KpiDtoList
  public static List<KpiDto> toKpiDtoList(List<Kpi> kpis) {
    List<KpiDto> result = new ArrayList<>();
    for (Kpi kpi : kpis) {
      result.add(toKpiDto(kpi));
    }
    return result;
  }
}
