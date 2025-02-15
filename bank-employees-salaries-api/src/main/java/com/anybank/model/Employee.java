package com.anybank.model;

import com.anybank.enum_model.JobStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank
    @Column(length = 30)
    String surname;
    @NotBlank
    @Column(length = 30)
    String firstname;
    @Column(length = 30)
    String lastname;
    @NotBlank
    @Column(length = 30)
    String gender;
    @Column
    @Pattern(regexp = "^[1-9]-\\d\\d\\d$")
    String phone;
    @NotBlank
    @Column
    @Email
    String email;
    @OneToOne
    @JoinColumn(name = "department_id")
    Department department;
    @OneToOne
    @JoinColumn(name = "position_id")
    Position position;
    @OneToOne
    @JoinColumn(name = "work_schedule_id")
    WorkSchedule workSchedule;
    @NotNull
    @Column(name = "date_of_admission")
    @FutureOrPresent
    LocalDateTime dateOfAdmission;
    @Column(name = "date_of_dismissal")
    @PastOrPresent
    LocalDateTime dateOfDismissal;
    @Column(name = "job_status")
    @Enumerated(EnumType.STRING)
    JobStatus jobStatus;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setSurname(String surname) {
        if (surname != null) this.surname = surname;
    }

    public void setFirstname(String firstname) {
        if (firstname != null) this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        if (lastname != null) this.lastname = lastname;
    }

    public void setGender(String gender) {
        if (gender != null) this.gender = gender;
    }

    public void setDepartment(Department department) {
        if (department != null) this.department = department;
    }

    public void setPhone(String phone) {
        if (phone != null) this.phone = phone;
    }

    public void setEmail(String email) {
        if (email != null) this.email = email;
    }

    public void setPositionId(Position position) {
        if (position != null) this.position = position;
    }

    public void setWorkScheduleId(WorkSchedule workSchedule) {
        if (workSchedule != null) this.workSchedule = workSchedule;
    }

    public void setDateOfAdmission(LocalDateTime dateOfAdmission) {
        if (dateOfAdmission != null) this.dateOfAdmission = dateOfAdmission;
    }

    public void setDateOfDismissal(LocalDateTime dateOfDismissal) {
        if (dateOfDismissal != null) this.dateOfDismissal = dateOfDismissal;
    }

    public void setJobStatus(JobStatus jobStatus) {
        if (jobStatus != null) this.jobStatus = jobStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(surname, employee.surname) && Objects.equals(firstname, employee.firstname) && Objects.equals(lastname, employee.lastname) && Objects.equals(gender, employee.gender) && Objects.equals(phone, employee.phone) && Objects.equals(email, employee.email) && Objects.equals(department, employee.department) && Objects.equals(position, employee.position) && Objects.equals(workSchedule, employee.workSchedule) && Objects.equals(dateOfAdmission, employee.dateOfAdmission) && Objects.equals(dateOfDismissal, employee.dateOfDismissal) && jobStatus == employee.jobStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, firstname, lastname, gender, phone, email, department, position, workSchedule, dateOfAdmission, dateOfDismissal, jobStatus);
    }
}
