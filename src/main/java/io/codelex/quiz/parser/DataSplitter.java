package io.codelex.quiz.parser;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataSplitter {

    List<String> splitQuestions(List<String> text) {
        List<String> questions = new ArrayList<>();
        String question = "";
        boolean isHeader = true;

        for(String line : text) {

            if (line.equals("---") && !isHeader) {
                questions.add(question);
                question = "";
            }
            else if(line.equals("---") && isHeader) {
                isHeader = false;
            }

            else if(!isHeader){
                question += line + "\n";
            }
        }
        questions.add(question);
        return questions;
    }

    String splitHeader(List<String> text) {
        String header = "";
        for (String line : text) {
            if(!line.equals("---")) {
                header += line + "\n";
            }
            else {
                break;
            }
        }
        return header;
    }

    String splitCredits(String header) {
        return StringUtils.substringBetween(header, "@credits", "@creditsEnd");
    }

    HashMap<String, String> splitSnippets(String header) {
        HashMap<String, String> snippets = new HashMap<>();

        String idFrom = "@snippet(id: '";
        String idTo = "')";

        Pattern idPattern = Pattern.compile(Pattern.quote(idFrom) + "(?s)(.*?)" + Pattern.quote(idTo));
        Matcher idMatcher = idPattern.matcher(header);
        while (idMatcher.find()) {
            String id = idMatcher.group(1);
            String snippet = StringUtils.substringBetween(header, "@snippet(id: '" + id + "')", "@snippetEnd");
            snippets.put(id, snippet);

        }
        return snippets;
    }
}