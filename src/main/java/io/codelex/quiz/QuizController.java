package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.repocrawler.RepositoryGateway;
import io.codelex.quiz.service.GeneratePdfReport;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {
    private IQuizService service;
    private RepositoryGateway gateway;

    public QuizController(IQuizService service, RepositoryGateway gateway) {
        this.gateway = gateway;
        this.service = service;
    }

/*
    @CrossOrigin
    @PostMapping("/quiz/{count}")
    public ResponseEntity<List<AddQuestionRequest>> publicTestCreator(@RequestBody UrlList urlList, @PathVariable(value = "count", required = false) Long count) {
        try {
            if (count > 0) {
                List<AddQuestionRequest> rawTest = service.createQuestions(urlList);
                for (AddQuestionRequest question : rawTest) {
                    Collections.shuffle(question.getAnswers());
                }
                Collections.shuffle(rawTest);
                List<AddQuestionRequest> deployableTest = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    deployableTest.add(rawTest.get(i));
                }
                return new ResponseEntity<>(deployableTest, HttpStatus.OK);
            }
            return new ResponseEntity<>(service.createQuestions(urlList), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
*/

    @GetMapping("/quiz/{count}")
    public ResponseEntity<List<Question>> createQuiz(@PathVariable(value = "count") int count) {
        return new ResponseEntity<>(service.randomTestQuestions(count), HttpStatus.OK);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity deleteQuestion(@PathVariable("id") Long id) {
        try {
            service.deleteQuestionById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/quiz/pdf",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdf(@RequestBody UrlList urlList) {
        try {
            List<AddQuestionRequest> questions = service.createQuestions(urlList);
            ByteArrayInputStream bis = GeneratePdfReport.quizInputStream(questions);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=quiz.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
