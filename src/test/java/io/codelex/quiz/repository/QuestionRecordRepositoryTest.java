package io.codelex.quiz.repository;

import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuestionRecordRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    
    @Test
    public void should_find_20_random_questions() {
        //given
        List<QuestionRecord> questionRecordList = null;
        for (QuestionRecord questionRecord : questionRecordList) {
            for (AnswerRecord answerRecord : questionRecord.getAnswerRecords()) {
                answerRepository.save(answerRecord);
            }
            questionRepository.save(questionRecord);
        }
        int count = 0;
        //when
        List<QuestionRecord> questionRecordList1 = questionRepository.findRandomTestQuestions(count);
        //then
        System.out.println(questionRecordList1.size());
        System.out.println(questionRecordList1);
        
        Assertions.assertEquals(count, questionRecordList1.size());
    }
/*
    private AnswerRecord createAnswerObject() {
        return new AnswerRecord(
                1L,
                "Spring Boot"
                , false);
    }

    private List<QuestionRecord> get20questions() {
        List<QuestionRecord> questionList = new ArrayList<>();
        for (long i = 1L; i <= 20L; i++) {
            List<AnswerRecord> answersList = new ArrayList();
            answersList.add(new AnswerRecord(
                    i,
                    "String Boot 1",
                    false));
            answersList.add(new AnswerRecord(
                    i,
                    "String Boot 2",
                    false));
            answersList.add(new AnswerRecord(
                    i,
                    "String Boot 3",
                    true));
            questionList.add(new QuestionRecord(
                    "QuestionRecord number" + i,
                    answersList,
                    "io.codelex"
            ));
        }
        System.out.println(questionList);
        return questionList;
    }*/
}