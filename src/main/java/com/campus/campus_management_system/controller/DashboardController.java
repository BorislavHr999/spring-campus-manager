package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Professor; // Добавен Professor
import com.campus.campus_management_system.repository.StudentRepository;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.ProfessorRepository; // Добавено ProfessorRepository
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    public DashboardController(StudentRepository studentRepository, CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    // --- СТАТИСТИКА ЗА ТАБЛОТО ---
    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        return Map.of(
            "totalStudents", studentRepository.count(),
            "activeCourses", courseRepository.count()
        );
    }

    // --- СТУДЕНТИ ---
    @PostMapping("/add-demo-student")
    public Map<String, String> addDemoStudent() {
        Random rand = new Random();
        Student student = new Student();
        student.setFirstName("Тест");
        student.setLastName("Студент " + rand.nextInt(1000, 9999));
        student.setEmail("student" + rand.nextInt(1000, 9999) + "@campus.bg");
        student.setFacultyNumber("FAC" + rand.nextInt(1000, 9999));
        student.setAge(20 + rand.nextInt(5));
        studentRepository.save(student);
        return Map.of("status", "success");
    }

    @GetMapping("/latest-students")
    public List<Student> getLatestStudents() {
        return studentRepository.findTop5ByOrderByIdDesc();
    }

    @GetMapping("/all-students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @DeleteMapping("/delete-student/{id}")
    public Map<String, String> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return Map.of("status", "deleted");
        }
        return Map.of("status", "error");
    }

    @PostMapping("/add-real-student")
    public Map<String, String> addRealStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return Map.of("status", "success");
    }

    // --- КУРСОВЕ ---
    @GetMapping("/all-courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/add-demo-course")
    public Map<String, String> addDemoCourse() {
        String[] subjects = {"Основи на Java", "Spring Boot Masterclass", "DevOps Практики", "Алгоритми", "Бази данни"};
        Random rand = new Random();
        Course course = new Course();
        course.setName(subjects[rand.nextInt(subjects.length)] + " " + rand.nextInt(100, 999));
        course.setCredits(rand.nextInt(2, 10));
        courseRepository.save(course);
        return Map.of("status", "success");
    }

    @DeleteMapping("/delete-course/{id}")
    public Map<String, String> deleteCourse(@PathVariable Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return Map.of("status", "deleted");
        }
        return Map.of("status", "error");
    }

    // НОВ МЕТОД: Добавяне на реален курс от потребителя
    @PostMapping("/add-real-course")
    public Map<String, String> addRealCourse(@RequestBody Course course) {
        courseRepository.save(course);
        return Map.of("status", "success");
    }

    // --- ПРЕПОДАВАТЕЛИ (СПРИНТ 4) ---
    @GetMapping("/all-professors")
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @PostMapping("/add-demo-professor")
    public Map<String, String> addDemoProfessor() {
        Random rand = new Random();
        Professor prof = new Professor();
        prof.setFirstName("Д-р Тест");
        prof.setLastName("Преподавател " + rand.nextInt(100, 999));
        prof.setEmail("prof" + rand.nextInt(100, 999) + "@campus.bg");
        professorRepository.save(prof);
        return Map.of("status", "success");
    }

    @PostMapping("/add-real-professor")
    public Map<String, String> addRealProfessor(@RequestBody Professor professor) {
        professorRepository.save(professor);
        return Map.of("status", "success");
    }

    @DeleteMapping("/delete-professor/{id}")
    public Map<String, String> deleteProfessor(@PathVariable Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return Map.of("status", "deleted");
        }
        return Map.of("status", "error");
    }

    @PutMapping("/update-professor/{id}")
    public Map<String, String> updateProfessor(@PathVariable Long id, @RequestBody Professor updatedProf) {
        return professorRepository.findById(id).map(prof -> {
            prof.setTitle(updatedProf.getTitle());
            prof.setFirstName(updatedProf.getFirstName());
            prof.setLastName(updatedProf.getLastName());
            prof.setEmail(updatedProf.getEmail());
            professorRepository.save(prof);
            return Map.of("status", "success");
        }).orElse(Map.of("status", "error"));
    }

    @PutMapping("/update-course/{id}")
    public Map<String, String> updateCourse(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return courseRepository.findById(id).map(course -> {
            if (updates.containsKey("name")) course.setName((String) updates.get("name"));
            if (updates.containsKey("credits")) course.setCredits(Integer.parseInt(updates.get("credits").toString()));
            
            if (updates.containsKey("professorId") && updates.get("professorId") != null) {
                Long profId = Long.parseLong(updates.get("professorId").toString());
                professorRepository.findById(profId).ifPresent(course::setProfessor);
            }

            courseRepository.save(course);
            return Map.of("status", "success");
        }).orElse(Map.of("status", "error"));
    }
}