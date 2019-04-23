package io.codelex.quiz.authentication.Roles;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ADMIN,
    STUDENT,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
