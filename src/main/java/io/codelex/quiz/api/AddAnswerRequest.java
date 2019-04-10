package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddAnswerRequest {
    private String answer;
    private boolean correct;
    
    @JsonCreator
    public AddAnswerRequest(@JsonProperty("answer") String answer,
                            @JsonProperty("correct") boolean correct) {
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
}
