package com.anybank.mapper;

import com.anybank.dto.*;
import com.anybank.model.*;

import java.util.ArrayList;
import java.util.List;

public class KpiMapper {
    //Kpi to KpiDto
    public static KpiDto toKpiDto(Kpi kpi) {
        return KpiDto.builder()
                .id(kpi.getId())
                .personalKpi(kpi.getPersonalKpi())
                .teamKpi(kpi.getTeamKpi())
                .commonKpi(kpi.getCommonKpi())
                .employeeId(kpi.getEmployee().getId())
                .month(kpi.getMonth())
                .year(kpi.getYear())
                .build();
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
