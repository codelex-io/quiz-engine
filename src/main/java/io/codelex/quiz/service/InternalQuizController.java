package io.codelex.quiz.service;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalQuizController {

    private QuizService quizService;
    
    public InternalQuizController(QuizService quizService) {
        this.quizService = quizService;
    }
    
}
