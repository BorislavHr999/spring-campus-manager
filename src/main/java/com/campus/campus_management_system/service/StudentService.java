package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setFirstName(studentDetails.getFirstName());
        existingStudent.setLastName(studentDetails.getLastName());
        existingStudent.setEmail(studentDetails.getEmail());
        existingStudent.setAge(studentDetails.getAge());

        if (studentDetails.getAddress() != null) {
            existingStudent.setAddress(studentDetails.getAddress());
        }

        return studentRepository.save(existingStudent);
    }


    public Page<Student> getAllStudents(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            return studentRepository.searchStudents(keyword, pageable);
        }
        
        return studentRepository.findAll(pageable);
    }

    public Student createStudent(Student student) {
            if (studentRepository.existsByEmail(student.getEmail())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Този имейл вече е регистриран!");
            }
            if (studentRepository.existsByFacultyNumber(student.getFacultyNumber())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Този факултетен номер вече съществува!");
            }
            return studentRepository.save(student);
        }
}