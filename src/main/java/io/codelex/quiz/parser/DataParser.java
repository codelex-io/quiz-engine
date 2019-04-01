package io.codelex.quiz.parser;


import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<Question> parse(List<String> lines) {

        List<String> questions = new ArrayList<>();
        String question = "";

        for(String line : lines) {
            if (line.equals("---")) {
                questions.add(question);
                question = "";
            } else {
                question += line + "\n";
            }
        }
        return mapQuestions(questions);
    }

    private List<Question> mapQuestions(List<String> rawQuestions) {
        List<Question> questions = new ArrayList<>();

        for (String question : rawQuestions) {
            String[] tst = question.split("\n\n");
            List<Answer> answers = new ArrayList<>();

            for (int i = 1; i < tst.length; i++) {
                String line = tst[i];
                answers.add(new Answer(line, line.contains("*)")));
            }
            questions.add(new Question(tst[0], answers));
        }
        return questions;
    }
}
