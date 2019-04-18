package io.codelex.quiz.repository;

import io.codelex.quiz.api.AddAnswerRequest;
import io.codelex.quiz.api.Answer;

import java.util.function.Function;

public class MapAddAnswerToAnswer implements Function<AddAnswerRequest, Answer> {
    @Override
    public Answer apply(AddAnswerRequest request) {
        return new Answer(
                request.getAnswer()
        );
    }
}
