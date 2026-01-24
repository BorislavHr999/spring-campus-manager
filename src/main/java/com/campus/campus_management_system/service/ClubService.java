package com.campus.campus_management_system.service;

import com.campus.campus_management_system.model.entity.Club;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.repository.ClubRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public void addStudentToClub(Long studentId, Long clubId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        student.getClubs().add(club);
        studentRepository.save(student);
    }
}
