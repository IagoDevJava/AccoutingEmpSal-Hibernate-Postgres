package com.anybank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "positions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank
    @Column
    String name;
    @NotBlank
    @OneToOne
    @JoinColumn(name = "department_id")
    Department department;
    @OneToOne
    @JoinColumn(name = "grade_id")
    Grade grade;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public void setDepartment(Department department) {
        if (department != null) this.department = department;
    }

    public void setGrade(Grade grade) {
        if (grade != null) this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id) && Objects.equals(name, position.name) && Objects.equals(department, position.department) && Objects.equals(grade, position.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department, grade);
    }
}
