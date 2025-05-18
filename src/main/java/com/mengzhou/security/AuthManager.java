package com.mengzhou.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthManager {

    private final TokenHelper tokenHelper;

    public AuthManager(TokenHelper tokenHelper) {
        this.tokenHelper = tokenHelper;
    }

    public  ResponseEntity<String>  authenticate(String username, String password ) {
        if ("meng".equals(username) && "password123".equals(password)) {
            String token = this.tokenHelper.generateToken(username);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
