package io.codelex.quiz.repository;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.model.AnswerRecord;

import java.util.ArrayList;
import java.util.function.Function;

public class MapAnswerRecordToAnswer implements Function<AnswerRecord, Answer> {
    @Override
    public Answer apply(AnswerRecord record) {
        return new Answer(
                record.getAnswer(),
                record.isCorrectAnswer()
        );
    }
}