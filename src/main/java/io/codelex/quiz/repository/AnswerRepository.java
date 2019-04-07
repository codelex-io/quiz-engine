package io.codelex.quiz.repository;

import io.codelex.quiz.model.AnswerRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerRecord,Long> {
}
