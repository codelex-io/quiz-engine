package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String question;
    
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Answer> answers;
    
    private String credits;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question(@NotEmpty String question, String credits) {
        this.question = question;
        this.answers= new ArrayList<>();
        this.credits = credits;
    }

    @JsonCreator
    public Question(@JsonProperty("question") String question,
                    @JsonProperty("answers") List<Answer> answers,
                    @JsonProperty("credits") String credits) {
        this.question = question;
        this.answers = answers;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
}