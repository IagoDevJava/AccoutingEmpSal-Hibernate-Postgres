package com.anybank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "salaries_data")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalariesData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    @Column
    Double wage;
    @NotNull
    @Column
    Double bonus;
    @NotNull
    @UniqueElements
    @OneToOne
    @JoinColumn(name = "position_id")
    Position position;

    public void setId(Long id) {
        if (id != null) this.id = id;
    }

    public void setWage(Double wage) {
        if (wage != null) this.wage = wage;
    }

    public void setBonus(Double bonus) {
        if (bonus != null) this.bonus = bonus;
    }

    public void setPosition(Position position) {
        if (position != null) this.position = position;
    }

}