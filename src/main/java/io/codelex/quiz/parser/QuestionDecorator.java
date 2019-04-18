package io.codelex.quiz.parser;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
class QuestionDecorator {

    List<AddQuestionRequest> addSnippetsToQuestions(HashMap<String, String> snippets, List<AddQuestionRequest> list) {
        for (AddQuestionRequest question : list) {
            for (String key : snippets.keySet()) {
                if (question.getQuestion().contains("@insertSnippet('" + key + "')")) {
                    String newQuestion = question.getQuestion().replace("@insertSnippet('" + key + "')", snippets.get(key).trim()+"\n");
                    question.setQuestion(newQuestion);
                }
            }
        }
        return list;
    }
    List<AddQuestionRequest> addCreditsToQuestions(String credit,List<AddQuestionRequest> list){
        for (AddQuestionRequest question : list) {
            question.setQuestion(question.getQuestion()+"\n"+"credits: "+credit.trim());
        }
        return list;
    }

}
