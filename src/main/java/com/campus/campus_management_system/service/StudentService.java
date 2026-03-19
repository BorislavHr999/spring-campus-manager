package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.EnrollmentRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository; // НОВО

    @Autowired
    public StudentService(StudentRepository studentRepository, 
                          EnrollmentRepository enrollmentRepository) { // НОВО
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // МЕТОД ЗА ПОСТАВЯНЕ НА ОЦЕНКА
    public void updateGrade(Long studentId, Long courseId, Double grade) {
        // Проверка на оценката (Българска система 2-6)
        if (grade < 2.0 || grade > 6.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Оценката трябва да е между 2.00 и 6.00!");
        }

        // Намираме записа за този студент в този курс
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Студентът не е записан в този курс!"));

        enrollment.setGrade(grade);
        enrollmentRepository.save(enrollment);
    }

    // Взимане на всички студенти (с пагинация и търсене)
    public Page<Student> getAllStudents(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.trim().isEmpty()) {
            return studentRepository.searchStudents(keyword, pageable);
        }
        return studentRepository.findAll(pageable);
    }

    // Създаване на студент
    public Student createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Този имейл вече е регистриран!");
        }
        if (studentRepository.existsByFacultyNumber(student.getFacultyNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Този факултетен номер вече съществува!");
        }
        return studentRepository.save(student);
    }

    // Редактиране на студент
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Студентът не е намерен!"));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setFacultyNumber(studentDetails.getFacultyNumber());

        return studentRepository.save(existingStudent);
    }

    // Изтриване на студент
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}