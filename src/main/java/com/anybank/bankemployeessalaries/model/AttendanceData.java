package com.anybank.bankemployeessalaries.model;

import com.anybank.bankemployeessalaries.enum_model.AttendanceStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance_dataies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    @Column(name = "date_att")
    LocalDate dateAtt;
    @NotBlank
    @PositiveOrZero
    @Column(name = "employees_id")
    Integer employeeId;
    @NotBlank
    @Enumerated(EnumType.STRING)
    AttendanceStatus status;

    public void setId(Long id) {
        if (id != null) this.id = id;
    }

    public void setDateAtt(LocalDate dateAtt) {
        if (dateAtt != null) this.dateAtt = dateAtt;
    }

    public void setEmployeeId(Integer employeeId) {
        if (employeeId != null) this.employeeId = employeeId;
    }

    public void setStatus(AttendanceStatus status) {
        if (status != null) this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceData that = (AttendanceData) o;
        return Objects.equals(id, that.id) && Objects.equals(dateAtt, that.dateAtt) && Objects.equals(employeeId, that.employeeId) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAtt, employeeId, status);
    }
}