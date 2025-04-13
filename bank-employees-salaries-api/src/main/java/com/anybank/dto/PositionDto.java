package com.anybank.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionDto {
    Long id;
    String name;
    Long departmentId;
    Integer gradeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionDto that = (PositionDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(departmentId, that.departmentId)
                && Objects.equals(gradeId, that.gradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentId, gradeId);
    }
}
