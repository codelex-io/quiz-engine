package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {
    private QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }


    @PostMapping("/test")
    public ResponseEntity<List<Question>> testCreatePOJOS(@RequestBody UrlList urlList) {
        try {
            return new ResponseEntity<>(service.createQuestions(urlList), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/questions")
    public ResponseEntity<QuestionRecord> createTest(@RequestBody Question question) {
        return new ResponseEntity<>(service.saveQuestion(question), HttpStatus.OK);
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findQuestionById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("quizzes/{count}")
    public ResponseEntity<List<Question>> createQuiz(@PathVariable("count") int count) {
        return new ResponseEntity<>(service.randomTestQuestions(count), HttpStatus.OK);
    }

    @PutMapping("/test")
    public ResponseEntity<QuestionRecord> save(@RequestBody AddQuestionRequest question) {
        return new ResponseEntity<>(service.testSaving(question), HttpStatus.OK);
    }

    @DeleteMapping("/questions")
    public ResponseEntity deleteQuestion(@PathVariable("id") Long id) {
        try {
            service.deleteQuestionById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}