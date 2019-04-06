package io.codelex.quiz.service;

import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.QuestionRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class QuizServiceTest {
    private AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
    private QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private QuizService quizService = new QuizService(answerRepository, questionRepository);

    @Test
    public void should_save_answer_objects() {
        //given
        Answer answer = createIncorrectAnswerObject();
        Mockito.when(answerRepository.save(any())).thenReturn(answer);
        //when
        Answer answer1 = quizService.saveAnswer(answer);
        //then
        Assertions.assertEquals(answer, answer1);
    }

    @Test
    public void should_return_error_if_answer_is_null() {
        //given
        Answer answer = createIncorrectAnswerObject();
        //when
        Mockito.when(answerRepository.save(any()));
        //then
        Assertions.assertThrows(NullPointerException.class,()->{answerRepository.save(answer);});
    }

    @Test
    public void should_save_question_object() {
        //given
        Question question = createQuestionObject();
        Mockito.when(questionRepository.save(any())).thenReturn(question);
        //when
        Question question1 = quizService.saveQuestion(question);
        //then
        Assertions.assertEquals(question, question1);

    }

/*
    Question createQuestionObject() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(
                1L,
                "Spring Boot"
                , false));
        answers.add(new Answer(
                1L,
                "Spring Boot"
                , false));
        answers.add(new Answer(
                1L,
                "Spring Boot"
                , false));
        answers.add(new Answer(
                1L,
                "Spring Boot v2.0"
                , true));
        return new Question(
                "Who is the best?",
                answers,
                "io.codelex");
    }

    private List<Answer> getAnswers() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(
                null,
                "Spring boot",
                false
        ));
        answerList.add(new Answer(
                1L,
                null,
                false
        ));
        answerList.add(new Answer(
                1L,
                "Spring boot",
                false
        ));
        return answerList;
    }

    private Answer createAnswerObject() {
        return new Answer(
                1L,
                "Spring Boot"
                , false);
    }*/
private Question createQuestionObject(){
    return new Question("Jautājums?","io.codelex");
}
private Answer createIncorrectAnswerObject(){
    return new Answer("Atbilde",false);
}private Answer createCorrectAnswerObject(){
        return new Answer("Atbilde",true);
    }

}