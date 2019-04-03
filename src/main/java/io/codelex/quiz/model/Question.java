package io.codelex.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "questionId")
    private Long id;
    private String question;
    @OneToMany
    private List<Answer> answers;
    private String credits;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
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
    public void addAnswer(Answer answer){
        this.answers.add(answer);
        answer.setQuestionId(this.id);
    }
}