package io.codelex.quiz.parser;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class DataValidator {
    boolean isValid(List<String> questions) throws IOException {
        boolean validated = false;
        for (int i = 0; i < questions.size(); i++) {
            String question = questions.get(i);
            validated = hasAnyAnswers(question, i);
            if (validated) {
                break;
            }
        }
        return validated;
    }
    
    private boolean hasAnyAnswers(String question, int questionId) throws IOException {
        if (question.contains("a)") || question.contains("b)")) {
            return hasRightAnswer(question, questionId);
        }
        else {
            throw new IOException("Question " + questionId + " does not contain any answers!");
        }
    }
    
    private boolean hasRightAnswer(String question, int questionId) throws IOException {
        String[] lines = question.split("\n\n");
        boolean foundCorrectAnswer = false;

        for (String line : lines) {
            if (line.charAt(1) == '*') {
                foundCorrectAnswer = true;
                break;
            }
        }
        if (foundCorrectAnswer) {
            return hasNoDuplicates(question, questionId);
        }
        else {
            throw new IOException("Question " + questionId + " does not have a right answer!");
        }
    }
    
    private boolean hasNoDuplicates(String question, int questionId) throws IOException {
        String[] lines = question.split("\n\n");
        Set<String> items = new HashSet<>(Arrays.asList(lines));
        
        if(lines.length == items.size()) {
            return true;
        }
        else {
            throw new IOException("Question " + questionId + " has duplicate answers!");
        }
    }
}