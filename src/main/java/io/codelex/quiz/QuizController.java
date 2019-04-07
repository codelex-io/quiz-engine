package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.parser.DataFetcher;
import io.codelex.quiz.parser.DataParser;
import io.codelex.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {
    DataFetcher dataFetcher = new DataFetcher();
    DataParser parser = new DataParser();
    String url = "https://raw.githubusercontent.com/codelex-io/spring-quiz/master/web/codelex-01/QUIZME.md";
    QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/json")
    public ResponseEntity<List<QuestionRecord>> result() throws Exception {
        List<String> data = dataFetcher.fetchData(url);
        return new ResponseEntity<>(parser.parse(data), HttpStatus.OK);
    }

    @PostMapping("/generator")
    public String submit(@ModelAttribute String url) {
        return "quiz";
    }
    
    @PutMapping("/test")
    public ResponseEntity<QuestionRecord>save(@RequestBody AddQuestionRequest question){
        return new ResponseEntity<>(service.testSaving(question),HttpStatus.OK);
    }
}