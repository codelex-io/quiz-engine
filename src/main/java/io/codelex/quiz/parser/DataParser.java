package io.codelex.quiz.parser;

import io.codelex.quiz.api.AddAnswerRequest;
import io.codelex.quiz.api.AddQuestionRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class DataParser {

    List<AddQuestionRequest> parseQuestionsWithAnswers(List<String> questionsRaw) {
        List<AddQuestionRequest> questions = new ArrayList<>();

        for (String question : questionsRaw) {
            String[] lines = question.split("\n\n");
            List<AddAnswerRequest> answers = new ArrayList<>();
            String questionString = "";

            for (String temporary : lines) {
                if (temporary.charAt(1) == ')' || temporary.charAt(1) == '*') {
                    String[] split = temporary.split("\\)", 2);
                    temporary = split[1];
                    AddAnswerRequest answer = new AddAnswerRequest(temporary, temporary.contains("*)"));
                    answers.add(answer);
                } else if (!temporary.equals("\n\n")) {
                    questionString += temporary;
                }
            }
            questions.add(new AddQuestionRequest(questionString, answers));
        }
        return questions;
    }

}