package io.codelex.quiz.parser;


import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    public List<QuestionRecord> parse(List<String> lines) {

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

    private List<QuestionRecord> mapQuestions(List<String> rawQuestions) {
        List<QuestionRecord> questionRecords = new ArrayList<>();

        for (String question : rawQuestions) {
            String[] tst = question.split("\n\n");
            List<AnswerRecord> answerRecords = new ArrayList<>();

            for (int i = 1; i < tst.length; i++) {
                String line = tst[i];
            }
        }
        return questionRecords;
    }
}
