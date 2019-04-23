package io.codelex.quiz.repository;

import io.codelex.quiz.api.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student,Long> {
    
    @Query("select count (student) > 0 from Student student where student.email= :email")
    boolean isEmailPresent(@Param("email") String email);
    
    @Query("select student from Student student where student.email like :email")
    Student getStudentByEmail(@Param("email") String email);
}
