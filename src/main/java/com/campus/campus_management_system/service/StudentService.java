package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
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


    public List<Student> search(Specification<Student> spec) {
        return studentRepository.findAll(spec);
    }
}