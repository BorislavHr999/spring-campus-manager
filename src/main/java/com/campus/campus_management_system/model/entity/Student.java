package com.campus.campus_management_system.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String facultyNumber;
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Address address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    // МАХНАХМЕ @JsonIgnore ОТ ТУК, ЗА ДА СЕ ВИЖДА В БРАУЗЪРА!
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Enrollment> enrollments;

    @ManyToMany
    @JoinTable(
        name = "student_club",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Club> clubs;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "used_id", referencedColumnName = "id")
    private User user;

    public int getCourseCount() {
        if (this.enrollments == null) {
            return 0;
        }
        return this.enrollments.size();
    }

    public java.util.List<String> getEnrolledCourseNames() {
        if (this.enrollments == null) {
            return java.util.Collections.emptyList();
        }
        return this.enrollments.stream()
                  .map(e -> e.getCourse().getName())
                  .collect(java.util.stream.Collectors.toList());
    }
}