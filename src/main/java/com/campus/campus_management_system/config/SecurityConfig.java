package com.campus.campus_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. НАСТРОЙКА НА ПРАВИЛАТА (Кой до какво има достъп)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login.html", "/register.html", "/api/auth/register", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login.html") // КАЗВАМЕ: Използвай този HTML файл за логин
                .loginProcessingUrl("/login") // КАЗВАМЕ: Обработвай данните на този адрес (както е във формата)
                .defaultSuccessUrl("/index.html", true) // КАЗВАМЕ: При успех, прати човека в Главното табло
                .permitAll()
            ); 

        return http.build();
    }

    // 2. КРИПТИРАНЕ НА ПАРОЛИТЕ
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
