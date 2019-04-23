package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin-api")
public class InternalQuizController {
    private QuizService service;

    public InternalQuizController(QuizService service) {
        this.service = service;
    }

    @PutMapping("/quiz")
    public ResponseEntity<Question> addQuestions(@RequestBody AddQuestionRequest question) {
        
        return new ResponseEntity<>(service.saveQuestion(question), HttpStatus.OK);
    }

    @PostMapping("/quiz") public ResponseEntity<List<Question>> addQuestionsByRawMd(@RequestBody UrlList urlList) throws IOException {
        List<Question> readyQuestionList = new ArrayList<>();
        List<AddQuestionRequest> questionList = service.createQuestions(urlList);
        for (AddQuestionRequest question : questionList) {
            readyQuestionList.add(service.saveQuestion(question));
        }
        return new ResponseEntity<>(readyQuestionList, HttpStatus.OK);
    }
}
