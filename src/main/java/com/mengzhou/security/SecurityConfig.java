package com.mengzhou.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final AdminDetailsService adminDetailsService;
    private final AuthFilter authFilter;

    public SecurityConfig(AdminDetailsService adminDetailsService, AuthFilter jwtAuthFilter) {
        this.adminDetailsService = adminDetailsService;
        this.authFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/users").authenticated()  // Require auth for POST /users
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()       // Allow public access to GET /users
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(adminDetailsService)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
