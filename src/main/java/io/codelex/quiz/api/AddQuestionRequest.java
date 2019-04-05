package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddQuestionRequest {
    private String question;
    private List<AddAnswerRequest> answers;
    private String credits;

    @JsonCreator
    public AddQuestionRequest(@JsonProperty("question") String question, 
                              @JsonProperty("answers") List<AddAnswerRequest> answers, 
                              @JsonProperty("credits") String credits) {
        this.question = question;
        this.answers = answers;
        this.credits = credits;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<AddAnswerRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AddAnswerRequest> answers) {
        this.answers = answers;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
}
