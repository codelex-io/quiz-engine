package io.codelex.quiz.api;

public class Answer {
    private Long id;
    private String answer;

    public Answer() {
    }

    public Answer(Long id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    @Override
    public String toString() {
        return answer;
    }
    
}
