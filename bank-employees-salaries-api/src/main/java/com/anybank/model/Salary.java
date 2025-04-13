package com.anybank.model;

import org.openapitools.model.Department;
import org.openapitools.model.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "salary")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @OneToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
    @NotNull
    @OneToOne
    @JoinColumn(name = "department_id")
    Department department;
    @NotBlank
    @Column
    String month;
    @NotBlank
    @Column
    String year;
    @NotNull
    @Column
    Double payment;

    public void setId(Long id) {
        if (id != null) this.id = id;
    }

    public void setEmployeeId(Employee employee) {
        if (employee != null) this.employee = employee;
    }

    public void setMonth(String month) {
        if (month != null) this.month = month;
    }

    public void setYear(String year) {
        if (year != null) this.year = year;
    }

    public void setPayment(Double payment) {
        if (payment != null) this.payment = payment;
    }

    public void setDepartmentId(Department department) {
        if (department != null) this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Objects.equals(id, salary.id) && Objects.equals(employee, salary.employee) && Objects.equals(department, salary.department) && Objects.equals(month, salary.month) && Objects.equals(year, salary.year) && Objects.equals(payment, salary.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, department, month, year, payment);
    }
}