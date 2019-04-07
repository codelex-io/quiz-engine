package io.codelex.quiz.repository;

import io.codelex.quiz.model.QuestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionRecord, Long> {
    Optional<QuestionRecord> findById(Long id);

    @Query(value = "SELECT * from questions ORDER BY RANDOM() LIMIT :count",
            nativeQuery = true)
    List<QuestionRecord> findRandomTestQuestions(@Param("count") int count);
}
