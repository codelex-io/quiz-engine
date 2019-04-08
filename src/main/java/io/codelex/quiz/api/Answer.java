package io.codelex.quiz.api;

public class Answer {
    private String answer;
    private boolean isItCorrect;

    public Answer(String answer, boolean isItCorrect) {
        this.answer = answer;
        this.isItCorrect = isItCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isItCorrect() {
        return isItCorrect;
    }

    public void setItCorrect(boolean itCorrect) {
        isItCorrect = itCorrect;
    }
}
