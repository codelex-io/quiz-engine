package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.service.pdfservice.GeneratePdfReport;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class QuizController {
    private IQuizService service;

    public QuizController(IQuizService service) {
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
            List<Question> questions = service.createQuestions(urlList);
            ByteArrayInputStream bis = GeneratePdfReport.quizInputStream(questions);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=quiz.pdf");

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/quiz/markdown2pdf",
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateM2ToPdf(@RequestBody UrlList urlList) {
        try {
            service.createPDF(urlList);
            ClassPathResource pdfFile = new ClassPathResource("/home/arnolds/IdeaProjects/quiz-engine/src/main/resources/boomshakalaka");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            return new ResponseEntity<>(new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
