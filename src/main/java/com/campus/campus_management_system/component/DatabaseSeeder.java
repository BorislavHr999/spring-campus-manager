package com.campus.campus_management_system.component;

import com.campus.campus_management_system.model.entity.User;
import com.campus.campus_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Проверяваме дали вече имаме създаден администратор
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123")) // Криптираме паролата преди запис!
                    .role("ROLE_ADMIN")
                    .build();
            userRepository.save(admin);
            System.out.println("✅ Създаден е начален администратор: admin / admin123");
        }

        // 2. Проверяваме дали вече имаме създаден обикновен потребител
        if (userRepository.findByUsername("student").isEmpty()) {
            User student = User.builder()
                    .username("student")
                    .password(passwordEncoder.encode("student123")) // Криптираме паролата
                    .role("ROLE_USER")
                    .build();
            userRepository.save(student);
            System.out.println("✅ Създаден е начален потребител: student / student123");
        }
    }
}
