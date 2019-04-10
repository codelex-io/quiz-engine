package io.codelex.quiz.api;

public class Answer {
    private String answer;
    private boolean correct;

    public Answer() {
    }

    public Answer(String answer, boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Answer{");
        sb.append("answer='").append(answer).append('\'');
        sb.append(", correct=").append(correct);
        sb.append('}');
        return sb.toString();
    }
}
