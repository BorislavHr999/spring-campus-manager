package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Professor;
// ВАЖНО: Провери дали импортите за Enrollment съвпадат с твоите имена на класове!
import com.campus.campus_management_system.model.entity.Enrollment; 
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.ProfessorRepository;
import com.campus.campus_management_system.repository.EnrollmentRepository; 
import com.campus.campus_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    // --- НОВО: Repository за оценките и записванията ---
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // 1. Взимане на всички курсове
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // 2. Създаване на нов курс (защитено)
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            // Ако от фронтенда идва курс с избран преподавател (само по ID)
            if (course.getProfessor() != null && course.getProfessor().getId() != null) {
                Optional<Professor> profOpt = professorRepository.findById(course.getProfessor().getId());
                if (profOpt.isPresent()) {
                    course.setProfessor(profOpt.get()); // Закачаме истинския обект от базата
                } else {
                    return ResponseEntity.status(404).body(Map.of("message", "Преподавателят не е намерен!"));
                }
            }
            Course savedCourse = courseRepository.save(course);
            return ResponseEntity.ok(savedCourse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при създаване: " + e.getMessage()));
        }
    }

    // 3. Изтриване на курс
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    // ==========================================
    // МЕТОДИ ЗА УПРАВЛЕНИЕ НА ПРЕПОДАВАТЕЛИ В КУРС
    // ==========================================

    // 4. Назначаване на преподавател към курс (през отделен URL)
    @PutMapping("/{courseId}/professor/{professorId}")
    public Course assignProfessorToCourse(@PathVariable Long courseId, @PathVariable Long professorId) {
        return courseService.assignProfessor(courseId, professorId);
    }

    // 5. Премахване на преподавател от курс
    @DeleteMapping("/{courseId}/professor")
    public Course removeProfessorFromCourse(@PathVariable Long courseId) {
        return courseService.removeProfessor(courseId);
    }

    // 6. Обновяване на курс
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        try {
            Optional<Course> courseOpt = courseRepository.findById(id);
            if (courseOpt.isPresent()) {
                Course existingCourse = courseOpt.get();
                
                // --- ЗАПАМЕТЯВАМЕ ВСИЧКИ ДАННИ ---
                existingCourse.setName(courseDetails.getName());
                existingCourse.setCredits(courseDetails.getCredits());
                
                // НОВИ ПОЛЕТА ЗА ГРАФИКА
                existingCourse.setDayOfWeek(courseDetails.getDayOfWeek());
                existingCourse.setStartTime(courseDetails.getStartTime());
                existingCourse.setEndTime(courseDetails.getEndTime());

                // Ръчно и безопасно закачане на професора
                if (courseDetails.getProfessor() != null && courseDetails.getProfessor().getId() != null) {
                    Optional<Professor> profOpt = professorRepository.findById(courseDetails.getProfessor().getId());
                    if (profOpt.isPresent()) {
                        existingCourse.setProfessor(profOpt.get());
                    } else {
                        return ResponseEntity.status(404).body(Map.of("message", "Преподавателят не е намерен!"));
                    }
                } else {
                    existingCourse.setProfessor(null); // Ако потребителят е избрал "Няма"
                }

                Course updatedCourse = courseRepository.save(existingCourse);
                return ResponseEntity.ok(updatedCourse);
            }
            return ResponseEntity.status(404).body(Map.of("message", "Курсът не е намерен!"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при обновяване: " + e.getMessage()));
        }
    }

    // ==========================================
    // НОВО: МЕТОД ЗА ИЗВЛИЧАНЕ НА СТУДЕНТИТЕ ЗА ОЦЕНЯВАНЕ
    // ==========================================
    @GetMapping("/{id}/students")
    public ResponseEntity<?> getStudentsInCourse(@PathVariable Long id) {
        try {
            // Взимаме списък с всички записани студенти за този курс
            List<Enrollment> enrollments = enrollmentRepository.findByCourseId(id);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Грешка при зареждане на студентите: " + e.getMessage()));
        }
    }
}

