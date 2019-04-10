package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.QuestionRecord;

import java.util.List;

public interface IQuizService {
    List<Question> createQuestions(UrlList urlList) throws Exception;

    Question findQuestionById(Long id);

    void deleteAnswerById(Long id);

    void deleteQuestionById(Long id);

    List<Question> randomTestQuestions(int questionCount);

    QuestionRecord saveQuestion(Question question);

    QuestionRecord testSaving(AddQuestionRequest request);
}
