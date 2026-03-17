package com.campus.campus_management_system.repository;

import com.campus.campus_management_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring Data JPA автоматично ще генерира SQL заявката за това!
    Optional<User> findByUsername(String username);
}
