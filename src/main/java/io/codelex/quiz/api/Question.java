package io.codelex.quiz.api;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<Answer> answerList;
    private String credits;

    public Question(String question, List<Answer> answerList, String credits) {
        this.question = question;
        this.answerList = answerList;
        this.credits = credits;
    }
    public Question(String question, List<Answer> answerList) {
        this.question = question;
        this.answerList = answerList;
        this.credits=null;
    }

    public Question(String question) {
        this.question = question;
        this.answerList=new ArrayList<>();
        this.credits=null;
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
}
