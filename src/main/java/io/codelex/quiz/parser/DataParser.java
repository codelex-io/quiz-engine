package io.codelex.quiz.parser;


import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DataParser {

    public List<Question> parseQuestions(List<String> lines) {
        List<Question> questions = new ArrayList<>();

        for (String question : lines) {
            String[] tst = question.split("\n\n");
            List<Answer> answers = new ArrayList<>();

            if(question.charAt(1) == ')' || question.charAt(1) == '*' && !question.equals("\n\n")) {
                answers.add(new Answer(question, question.contains("*)")));
            }
            else {

            }

            for (int i = 1; i < tst.length; i++) {
                String line = tst[i];
                answers.add(new Answer(line, line.contains("*)")));
            }
            questions.add(new Question(tst[0], answers));
        }
        return questions;
    }

}
