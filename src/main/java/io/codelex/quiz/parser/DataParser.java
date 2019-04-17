package io.codelex.quiz.parser;

import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataParser {

    public List<Question> parseQuestionsWithAnswers(List<String> questionsRaw) {
        List<Question> questions = new ArrayList<>();

        for (String question : questionsRaw) {
            String[] lines = question.split("\n\n");
            List<Answer> answers = new ArrayList<>();
            String questionString = "";

            for (int i = 0; i < lines.length; i++) {
                String temporary = lines[i];
                if(temporary.charAt(1) == ')' || temporary.charAt(1) == '*') {
                    Answer answer = new Answer("",temporary.contains("*)"));
                    String[] split = temporary.split("\\)", 2);
                    temporary=split[1].replaceAll("`|sql", "").trim();
                    answer.setAnswer(temporary);
                    answers.add(answer);
                }
                else if(!temporary.equals("\n\n")) {
                    questionString += temporary.replaceAll("\\r\\n|\\r|\\n|\\t", " ").trim(); 
                }
            }
            questions.add(new Question(questionString, answers));
        }
        return questions;
    }

}