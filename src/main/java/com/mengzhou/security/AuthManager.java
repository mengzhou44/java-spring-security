package com.mengzhou.security;

import com.mengzhou.entities.Admin;
import com.mengzhou.repositories.AdminRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {

    private final TokenHelper  tokenHelper;

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthManager(   TokenHelper  tokenHelper, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.tokenHelper = tokenHelper;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  ResponseEntity<String>  authenticate(String name, String password ) {
        Admin admin = adminRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (passwordEncoder.matches(password, admin.getPassword())) {
            var token = tokenHelper.generateToken(admin.getName());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
