package io.codelex.quiz.service;

import io.codelex.quiz.StudentService;
import io.codelex.quiz.api.AddStudentRequest;
import io.codelex.quiz.api.Student;
import io.codelex.quiz.repository.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements StudentService {

    private Password encoder;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(Password encoder, StudentRepository studentRepository) {
        this.encoder = encoder;
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(AddStudentRequest request) {
        Student student = new Student(
                encoder.passwordEncoder().encode(request.getPassword()),
                request.getEmail(),
                request.getFirstName(),
                request.getLastName());
        student = studentRepository.save(student);
        return student;
    }

    @Override
    public boolean isEmailPresent(String email) {
        return studentRepository.isEmailPresent(email);
    }

    @Override
    public boolean isPasswordMatching(String email, String password) {
        return false;
    }

    @Override
    public Student findStudent(String email) {
        return null;
    }
}
