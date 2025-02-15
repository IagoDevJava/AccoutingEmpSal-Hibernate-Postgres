package com.anybank.model;

import com.anybank.enum_model.AttendanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance_data")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttendanceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    @DateTimeFormat
    @Column(name = "date_att")
    LocalDateTime dateAtt;
    @NotBlank
    @OneToOne
    @JoinColumn(name = "employee_id")
    Employee employee;
    @NotBlank
    @Enumerated(EnumType.STRING)
    AttendanceStatus status;

    public void setId(Long id) {
        if (id != null) this.id = id;
    }

    public void setDateAtt(LocalDateTime dateAtt) {
        if (dateAtt != null) this.dateAtt = dateAtt;
    }

    public void setEmployeeId(Employee employee) {
        if (employee != null) this.employee = employee;
    }

    public void setStatus(AttendanceStatus status) {
        if (status != null) this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceData that = (AttendanceData) o;
        return Objects.equals(id, that.id) && Objects.equals(dateAtt, that.dateAtt) && Objects.equals(employee, that.employee) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAtt, employee, status);
    }
}