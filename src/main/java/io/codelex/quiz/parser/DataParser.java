package io.codelex.quiz.parser;

import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import java.util.ArrayList;
import java.util.List;

 class DataParser {

     List<Question> parseQuestionsWithAnswers(List<String> questionsRaw) {
        List<Question> questions = new ArrayList<>();

        for (String question : questionsRaw) {
            String[] lines = question.split("\n\n");
            List<Answer> answers = new ArrayList<>();
            String questionString = "";

            for (String temporary : lines) {
                if (temporary.charAt(1) == ')' || temporary.charAt(1) == '*') {
                    String[] split = temporary.split("\\)", 2);
                    temporary=split[1];
                    Answer answer = new Answer(temporary, temporary.contains("*)"));
                    answers.add(answer);
                } else if (!temporary.equals("\n\n")) {
                    questionString += temporary;
                }
            }
            questions.add(new Question(questionString, answers));
        }
        return questions;
    }

}