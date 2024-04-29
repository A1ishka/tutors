package com.makogon.tutor;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMINISTRATOR,
    TUTOR,
    STUDENT,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}