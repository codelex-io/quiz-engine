package io.codelex.quiz.model;

import jdk.jfr.Enabled;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class StudentRecord {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    
    

    public StudentRecord(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
