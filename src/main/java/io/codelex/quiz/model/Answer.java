package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
    private String answer;
    private boolean isCorrectAnswer;

    public Answer(@JsonProperty("answer") String answer,
                  @JsonProperty("iscorrect") boolean isCorrectAnswer) {
        this.answer = answer;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }
}