package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Answer {
    private String answer;
    private boolean correct;

    public Answer() {
    }

    @JsonCreator
    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        final StringBuilder string = new StringBuilder("Answer{");
        string.append("answer='").append(answer).append('\'');
        string.append(", correct=").append(correct);
        string.append('}');
        return string.toString();
    }
}
