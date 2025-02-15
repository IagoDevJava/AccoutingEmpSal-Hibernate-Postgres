package com.anybank.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class KpiDto {
    Long id;
    Double personalKpi;
    Double teamKpi;
    Double commonKpi;
    Integer employeeId;
    String month;
    String year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KpiDto kpiDto = (KpiDto) o;
        return Objects.equals(id, kpiDto.id) && Objects.equals(personalKpi, kpiDto.personalKpi) && Objects.equals(teamKpi, kpiDto.teamKpi) && Objects.equals(commonKpi, kpiDto.commonKpi) && Objects.equals(employeeId, kpiDto.employeeId) && Objects.equals(month, kpiDto.month) && Objects.equals(year, kpiDto.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalKpi, teamKpi, commonKpi, employeeId, month, year);
    }
}
