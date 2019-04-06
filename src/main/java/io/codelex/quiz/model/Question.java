package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @NotEmpty
    private String question;
    @NotBlank
    private String credits;
    @NotNull
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true,mappedBy = "question")
    private List<Answer> answers;
    @JsonCreator
    public Question(@JsonProperty("question")@NotEmpty String question,
                    @JsonProperty("credits")@NotBlank String credits,
                    @JsonProperty("answers")@NotNull List<Answer> answers) {
        this.question = question;
        this.credits = credits;
        this.answers = answers;
    }

    public Question(@NotEmpty String question, @NotBlank String credits) {
        this.question = question;
        this.credits = credits;
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
        return questionId;
    }

    public void setId(Long id) {
        this.questionId = id;
    }

}