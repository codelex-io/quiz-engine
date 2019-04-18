package io.codelex.quiz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;
    private String question;
    private List<Answer> answerList;
    private String credits;

    public Question() {
    }

    @JsonCreator
    public Question(@JsonProperty("question") String question,
                    @JsonProperty("answerList") List<Answer> answerList,
                    @JsonProperty("credits") String credits,
                    @JsonProperty("id") Long id) {
        this.id = id;
        this.question = question;
        this.answerList = answerList;
        this.credits = credits;
    }

    public Question(String question, List<Answer> answerList) {
        this.question = question;
        this.answerList = answerList;
        this.credits = null;
    }

    public Question(String question) {
        this.question = question;
        this.answerList = new ArrayList<>();
        this.credits = null;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        String questionReturn = "";
        questionReturn += "\n" + question;
        for (Answer answer : answerList) {
            questionReturn += "\n" + answer.toString();
        }
        return questionReturn;
    }
}
