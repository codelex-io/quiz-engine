package io.codelex.quiz.parser;

import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
                    // Šeit mums vajadzētu apgriezt Stringu, lai objecti izveidojas bez /n un "a)"
                    Answer answer = new Answer("",temporary.contains("*)"));
                    String[] split = temporary.split("\\)", 2);
                    temporary=split[1].replaceAll("\\r\\n|\\r|\\n|`", " ").trim();
                    answer.setAnswer(temporary);
                    answers.add(answer);
                    //šis ir viens variants kā to darīt... jaizdomā, ko iesākt ar "sql", kas ir sākumā griez nost vai atstāt
                    //ko daram, kad atbilde ir tabula ... psc
                   // answers.add(new Answer(temporary, temporary.contains("*)")));
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