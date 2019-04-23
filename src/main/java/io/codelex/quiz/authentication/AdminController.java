package io.codelex.quiz.authentication;

import io.codelex.quiz.api.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin-api")
class AdminController {
    
    
    
    @GetMapping("/account")
    public String account(Principal principal) {
        return principal.getName();
    }

   /* @GetMapping("/students/{email}")
    public ResponseEntity<Student> findStudentByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(.findById(email),HttpStatus.OK);
        } catch (
                NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}