package io.codelex.quiz.parser;

import io.codelex.quiz.api.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class QuestionDecoratorTest {
    private QuestionDecorator questionDecorator = new QuestionDecorator();
/*
    @Test
    void addSnippetsToQuestions() {
        //given
        HashMap<String, String> snippetCache = new HashMap<>();
        snippetCache.put(
                "tables", "|Column  |Column1|\n" +
                "|--------|-------|\n" +
                "|Desa    |Maize  |\n");  
        snippetCache.put(
                "chairs", "|Column  |Column1|\n" +
                "|--------|-------|\n" +
                "|Desa    |Maize  |\n");
        snippetCache.put(
                "brains", "|Column  |Column1|\n" +
                        "|--------|-------|\n" +
                        "|Desa    |Maize  |\n");
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("@insertSnippet(id: 'tables')Select one of these options"));
        questionList.add(new Question("@insertSnippet(id: 'chairs')Select one of these options"));
        questionList.add(new Question("@insertSnippet(id: 'brains')Select one of these options"));
        String expectedResponse =
                "|Column  |Column1|\n" +
                "|--------|-------|\n" +
                "|Desa    |Maize  |\n" + 
                "Select one of these options";
        String expectedResponse1 =
                "|Column  |Column1|\n" +
                        "|--------|-------|\n" +
                        "|Desa    |Maize  |\n" +
                        "Select one of these options";
        String expectedResponse2 =
                "|Column  |Column1|\n" +
                        "|--------|-------|\n" +
                        "|Desa    |Maize  |\n" +
                        "Select one of these options";

        //when
        String question = questionDecorator.addSnippetsToQuestions(snippetCache, questionList).get(0).getQuestion();
        String question1 = questionDecorator.addSnippetsToQuestions(snippetCache, questionList).get(1).getQuestion();
        String question2 = questionDecorator.addSnippetsToQuestions(snippetCache, questionList).get(2).getQuestion();
        //then
        Assertions.assertEquals(expectedResponse, question);
        Assertions.assertEquals(expectedResponse1, question1);
        Assertions.assertEquals(expectedResponse2, question2);
    }*/
}