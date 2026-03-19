package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Course;
import com.campus.campus_management_system.model.entity.Enrollment;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.AddressRepository;
import com.campus.campus_management_system.repository.CourseRepository;
import com.campus.campus_management_system.repository.DepartmentRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, 
                          CourseRepository courseRepository,
                          DepartmentRepository departmentRepository,
                          AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    // Взимане на всички студенти (с пагинация и търсене)
    public Page<Student> getAllStudents(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null && !keyword.trim().isEmpty()) {
            return studentRepository.searchStudents(keyword, pageable);
        }
        return studentRepository.findAll(pageable);
    }

    // Създаване на студент (със задължителни Катедра и Адрес)
    public Student createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Този имейл вече е регистриран!");
        }
        if (studentRepository.existsByFacultyNumber(student.getFacultyNumber())) {
            throw new RuntimeException("Този факултетен номер вече съществува!");
        }

        // --- НОВИ ЗАДЪЛЖИТЕЛНИ ПРОВЕРКИ ---
        if (student.getDepartment() == null || student.getDepartment().getId() == null) {
            throw new RuntimeException("Изборът на катедра е задължителен!");
        }
        if (student.getAddress() == null || student.getAddress().getId() == null) {
            throw new RuntimeException("Изборът на адрес е задължителен!");
        }

        return studentRepository.save(student);
    }

    // Редактиране на студент
    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Студентът не е намерен!"));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setFacultyNumber(studentDetails.getFacultyNumber());

        // --- ОБНОВЯВАНЕ НА КАТЕДРА И АДРЕС ---
        if (studentDetails.getDepartment() != null && studentDetails.getDepartment().getId() != null) {
            existingStudent.setDepartment(studentDetails.getDepartment());
        }
        if (studentDetails.getAddress() != null && studentDetails.getAddress().getId() != null) {
            existingStudent.setAddress(studentDetails.getAddress());
        }

        return studentRepository.save(existingStudent);
    }

    // Изтриване на студент
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}