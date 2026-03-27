package com.campus.campus_management_system.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore; // <-- ВАЖНО: Новият импорт!

@Entity
@Table(name = "professor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    private String email;

    private String title; // Доцент, Професор, Д-р и т.н.

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // --- НОВО: Връзка с акаунта (User) ---
    @OneToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore // <-- ВАЖНО: Това скрива User-а от JSON-а, за да няма безкраен цикъл!
    private User user;
}
