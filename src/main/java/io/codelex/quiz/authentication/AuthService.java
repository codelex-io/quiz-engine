package io.codelex.quiz.authentication;

import io.codelex.quiz.authentication.Roles.UserRoles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Collections.singleton;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class AuthService {

    public void authorise(String email, String role) {
        var authorities = singleton(new SimpleGrantedAuthority(role));
        var token = new UsernamePasswordAuthenticationToken(email, null, authorities);
        getContext().setAuthentication(token);
    }
    
    public void authoriseStudent(String email, String password){
        var studentAuthorities = singleton(new SimpleGrantedAuthority("ROLE_"+ UserRoles.STUDENT));
        var studentToken = new UsernamePasswordAuthenticationToken(email, password, studentAuthorities);
        getContext().getAuthentication();
    }

    public void clearAuthentication() {
        getContext().setAuthentication(null);
    }
}
