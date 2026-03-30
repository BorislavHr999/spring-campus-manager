package com.campus.campus_management_system.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Всеки изпит е вързан за конкретна специалност (курс)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private LocalDateTime examDate; // Дата и час на изпита

    @Column(nullable = false)
    private String room; // Зала за провеждане (напр. "Зала 314")

    @Column(nullable = false)
    private String examType; // Тип: "Редовен", "Поправителен", "Междинен тест"

    // Конструктори
    public Exam() {}

    public Exam(Course course, LocalDateTime examDate, String room, String examType) {
        this.course = course;
        this.examDate = examDate;
        this.room = room;
        this.examType = examType;
    }

    // Гетъри и Сетъри
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public LocalDateTime getExamDate() { return examDate; }
    public void setExamDate(LocalDateTime examDate) { this.examDate = examDate; }

    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }
}
