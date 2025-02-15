package com.anybank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Builder
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank
    @Column(length = 90, unique = true)
    String name;
    @Column
    String address;
    @Pattern(regexp = "^[1-9]-\\d\\d\\d$")
    @Column
    String phone;
    @Email
    @NotBlank
    @Column
    String email;
    @OneToOne
    @JoinColumn(name = "head_id")
    Employee head;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public void setAddress(String address) {
        if (address != null) this.address = address;
    }

    public void setPhone(String phone) {
        if (phone != null) this.phone = phone;
    }

    public void setEmail(String email) {
        if (email != null) this.email = email;
    }

    public void setHead(Employee head) {
        if (head != null) this.head = head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, head);
    }
}
