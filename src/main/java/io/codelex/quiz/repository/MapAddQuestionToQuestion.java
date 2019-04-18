package io.codelex.quiz.repository;

import io.codelex.quiz.api.AddAnswerRequest;
import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapAddQuestionToQuestion implements Function<AddQuestionRequest, Question> {
    MapAddAnswerToAnswer toAnswer = new MapAddAnswerToAnswer();
    @Override
    public Question apply(AddQuestionRequest request) {
        List<Answer> answerRequestList = new ArrayList<>();
        request.getAnswers().forEach(it -> answerRequestList.add(toAnswer.apply(it)));
        return new Question(
                request.getQuestion(),
                answerRequestList);
    }
}
