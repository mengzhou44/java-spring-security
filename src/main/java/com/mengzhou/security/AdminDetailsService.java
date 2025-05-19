package com.mengzhou.security;

import com.mengzhou.entities.Admin;
import com.mengzhou.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + name));

        return new org.springframework.security.core.userdetails.User(
                admin.getName(),
                admin.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(admin.getRole().name()))
        );
    }
}
