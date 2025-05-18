package com.mengzhou.controllers;
import com.mengzhou.security.AuthManager;

import com.mengzhou.security.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthManager authManager;

    public AuthController(AuthManager authManager) {
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String>  login(@RequestBody AuthRequest request) {
        return  authManager.authenticate(request.getUsername(), request.getPassword());
    }
}
