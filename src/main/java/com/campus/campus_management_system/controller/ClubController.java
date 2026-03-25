package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Club;
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.ClubRepository;
import com.campus.campus_management_system.repository.StudentRepository;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    // 1. Взимане на всички клубове (Достъпно за всички)
    @GetMapping
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    // 2. Създаване на нов клуб (Само Админ)
    @PostMapping
    public ResponseEntity<?> createClub(@RequestBody Club club, Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();
        return ResponseEntity.ok(clubRepository.save(club));
    }

    // 3. Редакция на клуб (Само Админ)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClub(@PathVariable Long id, @RequestBody Club clubData, Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();
        
        Optional<Club> clubOpt = clubRepository.findById(id);
        if (clubOpt.isPresent()) {
            Club existingClub = clubOpt.get();
            existingClub.setName(clubData.getName());
            existingClub.setDescription(clubData.getDescription());
            return ResponseEntity.ok(clubRepository.save(existingClub));
        }
        return ResponseEntity.status(404).body(Map.of("message", "Клубът не е намерен!"));
    }

    // 4. Изтриване на клуб (Само Админ)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClub(@PathVariable Long id, Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();
        
        try {
            // Първо премахваме клуба от списъка на всички студенти, за да не гръмне базата
            Optional<Club> clubOpt = clubRepository.findById(id);
            if (clubOpt.isPresent()) {
                Club club = clubOpt.get();
                for (Student student : club.getStudents()) {
                    student.getClubs().remove(club);
                    studentRepository.save(student);
                }
                clubRepository.delete(club);
                return ResponseEntity.ok(Map.of("message", "Клубът е изтрит успешно!"));
            }
            return ResponseEntity.status(404).body(Map.of("message", "Не е намерен такъв клуб."));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "Грешка при изтриване: " + e.getMessage()));
        }
    }

    // 5. Записване в клуб (За студенти)
    @PostMapping("/{clubId}/join")
    public ResponseEntity<?> joinClub(@PathVariable Long clubId, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        
        if (user == null || user.getStudent() == null) {
            return ResponseEntity.status(403).body(Map.of("message", "Само регистрирани студенти могат да се записват в клубове."));
        }

        Student student = user.getStudent();
        Club club = clubRepository.findById(clubId).orElse(null);

        if (club == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Клубът не е намерен!"));
        }

        if (student.getClubs().contains(club)) {
            return ResponseEntity.badRequest().body(Map.of("message", "Вече сте член на този клуб!"));
        }

        student.getClubs().add(club);
        studentRepository.save(student);

        return ResponseEntity.ok(Map.of("message", "Успешно се присъединихте към клуб: " + club.getName()));
    }

    // --- Помощни методи ---
    private boolean isAdmin(Authentication authentication) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private ResponseEntity<?> forbiddenResponse() {
        return ResponseEntity.status(403).body(Map.of("message", "Нямате права за това действие!"));
    }
}
