package io.codelex.quiz.service;

import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.parser.PojoCreator;
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
    private PojoCreator pojoCreator = Mockito.mock(PojoCreator.class);
    private QuizService quizService = new QuizService(answerRepository, questionRepository, pojoCreator);
/*
    @Test
    public void should_save_answer_objects() {
        //given
        AnswerRecord answerRecord = createIncorrectAnswerObject();
        Mockito.when(answerRepository.save(any())).thenReturn(answerRecord);
        //when
        AnswerRecord answerRecord1 = quizService.saveAnswer(answerRecord);
        //then
        Assertions.assertEquals(answerRecord, answerRecord1);
    }

    @Test
    public void should_return_error_if_answer_is_null() {
        //given
        AnswerRecord answerRecord = createIncorrectAnswerObject();
        //when
        Mockito.when(answerRepository.save(any()));
        //then
        Assertions.assertThrows(NullPointerException.class, () -> {
            answerRepository.save(answerRecord);
        });
    }

    @Test
    public void should_save_question_object() {
        //given
        QuestionRecord questionRecord = createQuestionObject();
        Mockito.when(questionRepository.save(any())).thenReturn(questionRecord);
        //when
        QuestionRecord questionRecord1 = quizService.saveQuestion(questionRecord);
        //then
        Assertions.assertEquals(questionRecord, questionRecord1);

    }*/

    @Test
    public void should_save_question_with_answer_list(){
        
    }

    QuestionRecord createQuestionObject() {
        List<AnswerRecord> answers = new ArrayList<>();
        for (long i = 0; i < 4; i++) {
            AnswerRecord record = new AnswerRecord();
            record.setAnswerId(i);
            record.setAnswer("Spring Boot" + i);
            record.setCorrectAnswer(false);
            answers.add(record);
        }
        AnswerRecord record = new AnswerRecord();
        record.setAnswerId(5L);
        record.setAnswer("Spring Boot");
        record.setCorrectAnswer(true);
        answers.add(record);
        QuestionRecord questionRecord = new QuestionRecord();
        questionRecord.setId(1L);
        questionRecord.setQuestion("What?");
        questionRecord.setCredits("io.codelex");
        answers.forEach(questionRecord::addAnswerRecord);
        return questionRecord;
    }


}