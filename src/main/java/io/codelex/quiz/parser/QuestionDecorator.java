package io.codelex.quiz.parser;

import io.codelex.quiz.api.Question;

import java.util.HashMap;
import java.util.List;

public class QuestionDecorator {

    public List<Question> addSnippetsToQuestions(HashMap<String, String> snippets, List<Question> list) {
        for (Question question:list) {
            for (String key: snippets.keySet()) {
                if(question.getQuestion().contains("@insertSnippet('"+key+"')")){
                    
                    String newQuestion=question.getQuestion().replace("@insertSnippet('"+key+"')",snippets.get(key));
                    question.setQuestion(newQuestion);
                }
            }
        }
    return list;
    }
}
