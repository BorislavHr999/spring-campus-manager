package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.User;
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

    @Autowired
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Взимане на текущия потребител (Това вече го имахме)
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

    // НОВ МЕТОД: Регистрация на нов потребител
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        // 1. Проверяваме дали името вече е заето
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Това потребителско име вече е заето!"));
        }

        // 2. Създаваме новия потребител
        User newUser = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // КРИПТИРАМЕ паролата!
                .role("ROLE_USER") // По подразбиране всички нови са обикновени потребители
                .build();
        
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "Регистрацията е успешна!"));
    }
}
