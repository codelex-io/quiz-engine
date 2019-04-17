package io.codelex.quiz.api;

import java.util.List;

public class Quiz {
    List<Question> questions;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (Question question:questions) {
            returnString+=question.toString();
        }
        return returnString;
    }
}
