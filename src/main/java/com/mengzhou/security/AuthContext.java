package com.mengzhou.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthContext {
    public String getName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null) ? auth.getName() : null;
    }

    public String getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities() != null && !auth.getAuthorities().isEmpty()) {
            return auth.getAuthorities().iterator().next().getAuthority(); // e.g. "ROLE_ADMIN"
        }
        return null;
    }
}
