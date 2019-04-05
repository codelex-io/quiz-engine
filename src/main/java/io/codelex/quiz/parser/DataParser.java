package io.codelex.quiz.parser;


import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DataParser {

    public List<Question> parseQuestions(List<String> lines) {
        return null;
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
