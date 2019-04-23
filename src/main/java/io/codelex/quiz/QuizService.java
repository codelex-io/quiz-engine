package io.codelex.quiz;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.QuestionRecord;

import java.io.IOException;
import java.util.List;

public interface QuizService {
    List<AddQuestionRequest> createQuestions(UrlList urlList) throws IOException;

    Question findQuestionById(Long id);

    void deleteAnswerById(Long id);

    void deleteQuestionById(Long id);

    List<Question> randomTestQuestions(int questionCount);

    Question saveQuestion(AddQuestionRequest question);

    QuestionRecord testSaving(AddQuestionRequest request);
    
}