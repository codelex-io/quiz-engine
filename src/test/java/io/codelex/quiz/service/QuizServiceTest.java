package io.codelex.quiz.service;

import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.QuestionRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class QuizServiceTest {
    private AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);
    private QuestionRepository questionRepository = Mockito.mock(QuestionRepository.class);
    private QuizService quizService = new QuizService(answerRepository, questionRepository);

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
        Assertions.assertThrows(NullPointerException.class,()->{answerRepository.save(answerRecord);});
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

    }

/*
    QuestionRecord createQuestionObject() {
        List<AnswerRecord> answers = new ArrayList<>();
        answers.add(new AnswerRecord(
                1L,
                "Spring Boot"
                , false));
        answers.add(new AnswerRecord(
                1L,
                "Spring Boot"
                , false));
        answers.add(new AnswerRecord(
                1L,
                "Spring Boot"
                , false));
        answers.add(new AnswerRecord(
                1L,
                "Spring Boot v2.0"
                , true));
        return new QuestionRecord(
                "Who is the best?",
                answers,
                "io.codelex");
    }

    private List<AnswerRecord> getAnswerRecords() {
        List<AnswerRecord> answerList = new ArrayList<>();
        answerList.add(new AnswerRecord(
                null,
                "Spring boot",
                false
        ));
        answerList.add(new AnswerRecord(
                1L,
                null,
                false
        ));
        answerList.add(new AnswerRecord(
                1L,
                "Spring boot",
                false
        ));
        return answerList;
    }

    private AnswerRecord createAnswerObject() {
        return new AnswerRecord(
                1L,
                "Spring Boot"
                , false);
    }*/
private QuestionRecord createQuestionObject(){
    return new QuestionRecord("Jautājums?","io.codelex");
}
private AnswerRecord createIncorrectAnswerObject(){
    return new AnswerRecord("Atbilde",false);
}private AnswerRecord createCorrectAnswerObject(){
        return new AnswerRecord("Atbilde",true);
    }

}