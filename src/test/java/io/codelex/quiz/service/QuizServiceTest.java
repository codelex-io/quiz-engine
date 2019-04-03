package io.codelex.quiz.service;

import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.QuestionRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class QuizServiceTest {
    private AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
    private QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private QuizService quizService = new QuizService(answerRepository,questionRepository);
    @Test
    public void should_save_answer_objects(){
        //given
        Answer answer = createAnswerObject();
        Mockito.when(answerRepository.save(any())).thenReturn(answer);
        //when
        Answer answer1 = quizService.saveAnswer(answer);
        //then
        Assertions.assertEquals(answer, answer1);
        
    }
    
    Question createQuestionObject(){
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(
                1L,
                "Spring Boot"
                ,false));
        answers.add(new Answer(
                1L,
                "Spring Boot"
                ,false));
        answers.add(new Answer(
                1L,
                "Spring Boot"
                ,false));
        answers.add(new Answer(
                1L,
                "Spring Boot v2.0"
                ,true));
        return new Question(
                "Who is the best?",
                answers,
                "io.codelex");
    }
    
    private Answer createAnswerObject(){
        return new Answer(
                1L,
                "Spring Boot"
                ,false);
    }

}