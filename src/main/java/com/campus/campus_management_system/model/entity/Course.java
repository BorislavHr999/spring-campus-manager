package com.campus.campus_management_system.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer credits;

    // --- НОВИ ПОЛЕТА ЗА УЧЕБЕН ГРАФИК ---
    private String dayOfWeek;   // напр. "Понеделник", "Вторник"
    private LocalTime startTime; // напр. 10:00
    private LocalTime endTime;   // напр. 12:00

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Professor professor;
   
}