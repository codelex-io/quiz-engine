package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @NotNull
    private String answer;
    @NotNull
    private boolean isCorrectAnswer;

    @JsonCreator
    public Answer(@JsonProperty("question") @NotEmpty Question question,
                  @JsonProperty("answer") @NotNull String answer,
                  @JsonProperty("isCorrectAnswer") @NotNull boolean isCorrectAnswer) {
        this.question = question;
        this.answer = answer;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public Answer(@NotNull String answer, @NotNull boolean isCorrectAnswer) {
        this.answer = answer;
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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