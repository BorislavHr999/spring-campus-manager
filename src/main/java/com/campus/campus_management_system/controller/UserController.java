package com.campus.campus_management_system.controller;

import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1. Взимане на всички акаунти (САМО ЗА АДМИНИ)
    @GetMapping
    public ResponseEntity<?> getAllUsers(Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();
        
        List<User> users = userRepository.findAll();
        // Добра практика: никога не връщай криптираните пароли към фронтенда!
        users.forEach(u -> u.setPassword("[ЗАЩИТЕНО]")); 
        
        return ResponseEntity.ok(users);
    }

    // 2. Смяна на роля (САМО ЗА АДМИНИ)
    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestParam String newRole, Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setRole(newRole);
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Ролята е обновена успешно!"));
        }
        return ResponseEntity.status(404).body(Map.of("message", "Потребителят не е намерен!"));
    }

    // 3. Смяна на парола от Админ (САМО ЗА АДМИНИ)
    @PutMapping("/{id}/password")
    public ResponseEntity<?> resetUserPassword(@PathVariable Long id, @RequestBody Map<String, String> payload, Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();

        String newPassword = payload.get("newPassword");
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Паролата не може да бъде празна!"));
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Криптираме новата парола преди да я запазим!
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Паролата е сменена успешно!"));
        }
        return ResponseEntity.status(404).body(Map.of("message", "Потребителят не е намерен!"));
    }

    // 4. Изтриване на акаунт (САМО ЗА АДМИНИ)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id, Authentication authentication) {
        if (!isAdmin(authentication)) return forbiddenResponse();

        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Акаунтът е изтрит успешно!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("message", "Грешка при изтриване (вероятно акаунтът е свързан със студентски профил)."));
        }
    }

    // --- Помощни методи ---
    private boolean isAdmin(Authentication authentication) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private ResponseEntity<?> forbiddenResponse() {
        return ResponseEntity.status(403).body(Map.of("message", "Достъпът е отказан! Само администратори."));
    }
}
