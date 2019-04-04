package io.codelex.quiz.repository;

import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void should_find_20_random_questions() {
        //given
        List<Question> questionList = get20questions();
        for (Question question : questionList) {
            for (Answer answer : question.getAnswers()) {
                answerRepository.save(answer);
            }
            questionRepository.save(question);
        }
        int count = 9;
        //when
        List<Question> questionList1 = questionRepository.findRandomTestQuestions(count);
        //then
        System.out.println(questionList1.size());
        System.out.println(questionList1);
        
        Assertions.assertEquals(count, questionList1.size());
    }

    private Answer createAnswerObject() {
        return new Answer(
                1L,
                "Spring Boot"
                , false);
    }

    private List<Question> get20questions() {
        List<Question> questionList = new ArrayList<>();
        for (Long i = 1L; i <= 20L; i++) {
            List<Answer> answersList = new ArrayList();
            answersList.add(new Answer(
                    i,
                    "String Boot 1",
                    false));
            answersList.add(new Answer(
                    i,
                    "String Boot 2",
                    false));
            answersList.add(new Answer(
                    i,
                    "String Boot 3",
                    true));
            questionList.add(new Question(
                    "Question number" + i,
                    answersList,
                    "io.codelex"
            ));
        }
        System.out.println(questionList);
        return questionList;
    }
}