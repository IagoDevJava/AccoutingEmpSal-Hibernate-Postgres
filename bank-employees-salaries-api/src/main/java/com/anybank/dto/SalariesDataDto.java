package com.anybank.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalariesDataDto {
    Long id;
    Double wage;
    Double bonus;
    Long positionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesDataDto that = (SalariesDataDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(wage, that.wage)
                && Objects.equals(bonus, that.bonus)
                && Objects.equals(positionId, that.positionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wage, bonus, positionId);
    }
}