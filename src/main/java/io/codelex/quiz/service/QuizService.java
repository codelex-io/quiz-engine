package io.codelex.quiz.service;

import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Component;

@Component 
public class QuizService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }


    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
    public Question saveQuestion(Question question){
        question.getAnswers()
                .forEach(it->answerRepository.save(it));
        return questionRepository.save(question);   
    }
}
