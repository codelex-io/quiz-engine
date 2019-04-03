package io.codelex.quiz;

import io.codelex.quiz.model.Question;
import io.codelex.quiz.parser.DataFetcher;
import io.codelex.quiz.parser.DataParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {
    DataFetcher dataFetcher = new DataFetcher();
    DataParser parser = new DataParser();
    String url = "https://raw.githubusercontent.com/codelex-io/spring-quiz/master/web/codelex-01/QUIZME.md";

    @GetMapping("/json")
    public ResponseEntity<List<Question>> result() throws Exception {
        List<String> data = dataFetcher.fetchData(url);
        return new ResponseEntity<>(parser.parse(data), HttpStatus.OK);
    }

    @PostMapping("/generator")
    public String submit(@ModelAttribute String url) {
        return "quiz";
    }
}