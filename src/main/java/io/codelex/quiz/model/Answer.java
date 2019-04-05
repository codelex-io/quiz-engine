package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long questionId;
    //------------------------- ID generated, questionId has to be set by question. questionId = Question generated id. 
    @NotNull
    private String answer;
    @NotNull
    private boolean isCorrectAnswer;

    @JsonCreator
    public Answer(
            @JsonProperty("questionId") Long questionId,
            @JsonProperty("answer") String answer,
                  @JsonProperty("isCorrect") boolean isCorrectAnswer) {
        this.answer = answer;
        this.isCorrectAnswer = isCorrectAnswer;
        this.questionId= questionId;
    }
    public Answer(
            @JsonProperty("answer") String answer,
            @JsonProperty("isCorrect") boolean isCorrectAnswer) {
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}