package io.codelex.quiz.repository;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.model.QuestionRecord;

import java.util.ArrayList;
import java.util.function.Function;

public class MapQuestionRecordToQuestion implements Function<QuestionRecord, Question> {
    private MapAnswerRecordToAnswer toAnswer = new MapAnswerRecordToAnswer();

    @Override
    public Question apply(QuestionRecord record) {
        ArrayList<Answer> answerList = new ArrayList<>();
        record.getAnswerRecords().forEach(it -> answerList.add(toAnswer.apply(it)));

        return new Question(
                record.getQuestion(),
                answerList,
                record.getCredits(),
                record.getId()
                );
    }
}