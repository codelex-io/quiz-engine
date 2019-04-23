package io.codelex.quiz.authentication.StudentAuthentication;

import io.codelex.quiz.StudentService;
import io.codelex.quiz.api.AddStudentRequest;
import io.codelex.quiz.api.Student;
import io.codelex.quiz.authentication.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/student-api")
class StudentAuthenticationController {
    private final AuthService authService;
    private final StudentService studentService;


    public StudentAuthenticationController(AuthService authService, StudentService studentService) {
        this.authService = authService;
        this.studentService = studentService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestBody Student student) {
        if (studentService.isEmailPresent(student.getEmail()) && studentService.isPasswordMatching(student.getEmail(), student.getPassword())) {
            authService.authoriseStudent(student.getEmail(), student.getPassword());
            return new ResponseEntity<>("Login succesful", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Inccorect Password or email", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Student> register(@RequestBody AddStudentRequest request) {
        if (!studentService.isEmailPresent(request.getEmail())) {
            Student registeredStudent = studentService.addStudent(request);
            return new ResponseEntity<>(registeredStudent, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sign-out")
    public void signOut() {
        authService.clearAuthentication();
    }

    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }
}
