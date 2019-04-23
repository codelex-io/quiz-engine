package io.codelex.quiz;

import io.codelex.quiz.api.AddStudentRequest;
import io.codelex.quiz.api.Student;
import org.springframework.stereotype.Component;

public interface StudentService {
    
    Student addStudent(AddStudentRequest request);
    
    boolean isEmailPresent(String email);
    
    boolean isPasswordMatching(String email, String password);
    
    Student findStudent(String email);
}
