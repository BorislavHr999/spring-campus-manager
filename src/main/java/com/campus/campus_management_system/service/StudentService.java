package com.campus.campus_management_system.service;

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

    // Създаване на студент
    public Student createStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Този имейл вече е регистриран!");
        }
        if (studentRepository.existsByFacultyNumber(student.getFacultyNumber())) {
            throw new RuntimeException("Този факултетен номер вече съществува!");
        }

        if (student.getDepartment() == null || student.getDepartment().getId() == null) {
            throw new RuntimeException("Изборът на катедра е задължителен!");
        }

        // ЗАПАЗВАМЕ АДРЕСА ПЪРВО, ЗА ДА ПОЛУЧИ ID
        if (student.getAddress() != null && student.getAddress().getCity() != null) {
            addressRepository.save(student.getAddress());
        } else {
             throw new RuntimeException("Въвеждането на град и улица е задължително!");
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

        if (studentDetails.getDepartment() != null && studentDetails.getDepartment().getId() != null) {
            existingStudent.setDepartment(studentDetails.getDepartment());
        }

        // ОБНОВЯВАМЕ АДРЕСА (вече е текст, записваме го в базата)
        if (studentDetails.getAddress() != null) {
            if (existingStudent.getAddress() != null) {
                // Ако студентът вече е имал адрес, просто му презаписваме текста
                existingStudent.getAddress().setCity(studentDetails.getAddress().getCity());
                existingStudent.getAddress().setStreet(studentDetails.getAddress().getStreet());
                existingStudent.getAddress().setCountry(studentDetails.getAddress().getCountry());
                addressRepository.save(existingStudent.getAddress());
            } else {
                // Ако е нямал адрес досега, създаваме новия
                addressRepository.save(studentDetails.getAddress());
                existingStudent.setAddress(studentDetails.getAddress());
            }
        }

        return studentRepository.save(existingStudent);
    }

    // Изтриване на студент
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}