package io.codelex.quiz.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataValidatorTest {

    private DataValidator validator = new DataValidator();
    
    @Test
    void validates_question_when_format_is_correct() throws Exception {
        //given
        String question = "Annotation for Controller Class\n" +
                "\n" +
                "a) @Before\n" +
                "\n" +
                "b*) @Controller\n" +
                "\n" +
                "c) @After\n" +
                "\n" +
                "d) @Exception";
        List<String> questions = new ArrayList<>();
        questions.add(question);
        
        //then
        assertTrue(validator.isValid(questions));
        
    }
    
    @Test
    void throwsExceptionIfNoAnswers() {
        //given
        String question = "Annotation for Controller Class\n" +
                "\n";
        List<String> questions = new ArrayList<>();
        questions.add(question);

        //then
        IOException thrown = assertThrows(IOException.class, () -> { validator.isValid(questions); });
        System.out.println(thrown.getMessage());
    }
    
    @Test
    void throwsExceptionIfNoCorrectAnswer() {
        //given
        String question = "Annotation for Controller Class\n" +
                "\n" +
                "a) @Before\n" +
                "\n" +
                "b) @Controller\n" +
                "\n" +
                "c) @After\n" +
                "\n" +
                "d) @Exception";
        List<String> questions = new ArrayList<>();
        questions.add(question);

        //then
        IOException thrown = assertThrows(IOException.class, () -> { validator.isValid(questions); });
        System.out.println(thrown.getMessage());
    }
    
    @Test
    void throwsExceptionIfDuplicatesFound() {
        //given
        String question = "Annotation for Controller Class\n" +
                "\n" +
                "a) @Before\n" +
                "\n" +
                "b*) @Controller\n" +
                "\n" +
                "c) @After\n" +
                "\n" +
                "a) @Before";
        List<String> questions = new ArrayList<>();
        questions.add(question);
        
        //then
        IOException thrown = assertThrows(IOException.class, () -> { validator.isValid(questions); });
        System.out.println(thrown.getMessage());
    }
}