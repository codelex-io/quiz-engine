package io.codelex.quiz.parser;

import org.springframework.stereotype.Component;

@Component
public class DataValidator {
    //todo unused?
    public String testingStringMatcher(String line) {
        String firstChar = Character.toString(line.charAt(0));
        String secondChar = Character.toString(line.charAt(1));
        return firstChar + secondChar;
    }
}
