package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class Student {
    @Email
    private String email;
    @Size(min = 6, max = 12)
    private String password;
    private String firstName;
    private String lastName;

    public Student() {
    }

    @JsonCreator
    public Student(@JsonProperty String email,
                   @JsonProperty String password,
                   @JsonProperty String firstName,
                   @JsonProperty String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
@JsonCreator
    public Student(@JsonProperty String email, 
                   @JsonProperty String password) {
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
