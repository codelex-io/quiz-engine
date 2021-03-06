package io.codelex.quiz.parser;

import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PojoCreator {
    private DataFetcher fetcher;
    private DataSplitter splitter;
    private DataValidator validator;
    private DataParser parser;
    private QuestionDecorator decorator;

    public PojoCreator(DataFetcher fetcher, DataSplitter splitter, DataValidator validator, DataParser parser, QuestionDecorator decorator) {
        this.fetcher = fetcher;
        this.splitter = splitter;
        this.validator = validator;
        this.parser = parser;
        this.decorator = decorator;
    }

    public List<Question> createQuestions(UrlList urlList) throws IOException {
        List<Question> list = new ArrayList<>();
        for (String it : urlList.getUrlList()) {
            List<String> stringList = fetcher.fetchData(it);
            List<String> rawQuestionString = splitter.splitQuestions(stringList);
            String rawHeader = splitter.splitHeader(stringList);
            HashMap<String, String> snippets = splitter.splitSnippets(rawHeader);
            list = parser.parseQuestionsWithAnswers(rawQuestionString);
            list = decorator.addSnippetsToQuestions(snippets, list);
            list = decorator.addCreditsToQuestions(splitter.splitCredits(rawHeader),list);
        }
        return list;
    }
}