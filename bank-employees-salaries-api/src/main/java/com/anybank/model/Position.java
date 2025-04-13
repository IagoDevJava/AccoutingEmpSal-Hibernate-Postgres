package com.anybank.model;

import org.openapitools.model.Department;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

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
  Long id;
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

  public void setId(Long id) {
      if (id != null) {
          this.id = id;
      }
  }

  public void setName(String name) {
      if (name != null) {
          this.name = name;
      }
  }

  public void setDepartment(Department department) {
      if (department != null) {
          this.department = department;
      }
  }

  public void setGrade(Grade grade) {
      if (grade != null) {
          this.grade = grade;
      }
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Position position = (Position) o;
    return Objects.equals(id, position.id) && Objects.equals(name, position.name) && Objects.equals(
        department, position.department) && Objects.equals(grade, position.grade);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, department, grade);
  }
}
