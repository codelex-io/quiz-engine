package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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

    public AddQuestionRequest(String question, List<AddAnswerRequest> answerList) {
        this.question = question;
        this.answers = answerList;
        this.credits = null;
    }

    public AddQuestionRequest(String question) {
        this.question = question;
        this.answers = new ArrayList<>();
        this.credits = null;
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

    public String getCredits() {
        return credits;
    }

}
