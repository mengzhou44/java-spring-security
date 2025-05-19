package com.mengzhou.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String hashedPassword = encoder.encode(rawPassword);

        System.out.println("Hashed password: " + hashedPassword);
    }
}
