package io.codelex.quiz.authentication.StudentAuthentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(100)
@Configuration
class StudentSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/student-api/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/student-api/sign-in", "/student-api/register").permitAll()
                .anyRequest().hasRole("STUDENT");
    }
}
