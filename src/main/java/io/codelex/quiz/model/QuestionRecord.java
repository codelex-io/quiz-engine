package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @NotEmpty
    private String question;
    @NotBlank
    private String credits;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "questionRecord")
    @JsonManagedReference
    private List<AnswerRecord> answerRecords;

    public QuestionRecord() {
    }


    public QuestionRecord(@NotEmpty String question, //todo validation?
                          @NotBlank String credits) {
        this.question = question;
        this.credits = credits;
        this.answerRecords = new ArrayList<>();
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

    public List<AnswerRecord> getAnswerRecords() {
        return answerRecords;
    }

    public void setAnswerRecords(List<AnswerRecord> answerRecords) {
        this.answerRecords = answerRecords;
    }

    public Long getId() {
        return questionId;
    }

    public void setId(Long id) {
        this.questionId = id;
    }

    public void addAnswerRecord(AnswerRecord answer) {
        this.answerRecords.add(answer);
        answer.setQuestionRecord(this);
    }

}