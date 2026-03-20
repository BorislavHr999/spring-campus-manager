package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.Address;
import com.campus.campus_management_system.model.entity.Department; // Добавен импорт за катедрата!
import com.campus.campus_management_system.model.entity.Student;
import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.AddressRepository;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository; 

    @Autowired
    public AuthController(UserRepository userRepository, 
                          PasswordEncoder passwordEncoder,
                          AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepository = addressRepository;
    }

    // Взимане на текущия потребител
    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(Authentication authentication) {
        Map<String, Object> userInfo = new HashMap<>();
        if (authentication != null) {
            userInfo.put("username", authentication.getName());
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            userInfo.put("roles", roles);
        }
        return userInfo;
    }

    // Регистрация на нов потребител
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> payload) { 
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        // 1. Проверяваме дали името вече е заето
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Това потребителско име вече е заето!"));
        }

        // 2. Създаваме новия потребител (Акаунт за логин)
        User newUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) 
                .role("ROLE_USER") 
                .build();

        // 3. Създаваме студентския профил към него
        Student newStudent = new Student();
        newStudent.setFirstName(payload.containsKey("firstName") ? (String) payload.get("firstName") : "Нов");
        newStudent.setLastName(payload.containsKey("lastName") ? (String) payload.get("lastName") : "Студент");
        newStudent.setEmail(payload.containsKey("email") ? (String) payload.get("email") : username + "@campus.com");

        // ---> НОВО: ОБРАБОТКА И ЗАПАЗВАНЕ НА КАТЕДРАТА <---
        if (payload.containsKey("department")) {
            Map<String, Object> deptMap = (Map<String, Object>) payload.get("department");
            if (deptMap != null && deptMap.containsKey("id")) {
                Long deptId = Long.valueOf(deptMap.get("id").toString());
                
                // Създаваме обект с ID-то, за да може Hibernate да го навърже
                Department department = new Department();
                department.setId(deptId);
                newStudent.setDepartment(department);
            }
        }

        // --- 4. ОБРАБОТКА И ЗАПАЗВАНЕ НА АДРЕСА ---
        if (payload.containsKey("address")) {
            Map<String, String> addressMap = (Map<String, String>) payload.get("address");
            
            Address newAddress = new Address();
            newAddress.setCity(addressMap.get("city"));
            newAddress.setStreet(addressMap.get("street"));
            newAddress.setCountry(addressMap.getOrDefault("country", "България"));
            
            // Задължително го запазваме първо в базата!
            addressRepository.save(newAddress);
            
            // Прикачваме го към студента
            newStudent.setAddress(newAddress);
        }

        // 5. Навързваме нещата и запазваме всичко
        newStudent.setUser(newUser);
        newUser.setStudent(newStudent);
        
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Регистрацията е успешна!"));
    }
}