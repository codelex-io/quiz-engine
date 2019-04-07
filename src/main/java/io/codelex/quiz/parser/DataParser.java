package io.codelex.quiz.parser;



import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DataParser {

    public List<Question> parseQuestions(List<String> questionsRaw) {
        List<Question> questions = new ArrayList<>();

        for (String question : questionsRaw) {
            String[] lines = question.split("\n\n");
            List<Answer> answers = new ArrayList<>();
            String questionString = "";

            for (int i = 0; i < lines.length; i++) {
                String temporary = lines[i];
                if(temporary.charAt(1) == ')' || temporary.charAt(1) == '*') {
                    answers.add(new Answer(temporary, temporary.contains("*)")));
                }
                else if(!temporary.equals("\n\n")) {
                    questionString += temporary + "\n\n";  //format goes here
                }
            }
            questions.add(new Question(questionString, answers));
        }
        return questions;
    }

}