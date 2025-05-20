package com.mengzhou.controllers;

import com.mengzhou.entities.User;
import com.mengzhou.helpers.PageResponse;
import com.mengzhou.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@EnableMethodSecurity
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public PageResponse<User> getUsers(Pageable pageable) {
        var  page = userRepository.findAll(pageable);
        return new PageResponse<>(page.getContent(),page.getNumberOfElements());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
