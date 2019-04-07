package io.codelex.quiz.parser;

public class DataValidator {
    public String testingStringMatcher(String line) {
        String firstChar = Character.toString(line.charAt(0));
        String secondChar = Character.toString(line.charAt(1));
        return firstChar + secondChar;
    }
}
