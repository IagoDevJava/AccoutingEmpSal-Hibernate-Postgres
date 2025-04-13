package com.anybank.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryDto {
    Long id;
    Long employeeId;
    Long departmentId;
    String month;
    String year;
    Double payment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryDto salaryDto = (SalaryDto) o;
        return Objects.equals(id, salaryDto.id) && Objects.equals(employeeId, salaryDto.employeeId) && Objects.equals(departmentId, salaryDto.departmentId) && Objects.equals(month, salaryDto.month) && Objects.equals(year, salaryDto.year) && Objects.equals(payment, salaryDto.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, departmentId, month, year, payment);
    }
}