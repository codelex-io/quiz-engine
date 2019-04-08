package io.codelex.quiz.parser;

import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PojoCreator {
    private DataFetcher fetcher = new DataFetcher();
    private DataSplitter splitter = new DataSplitter();
    private DataValidator validator = new DataValidator();
    private DataParser parser = new DataParser();
    private QuestionDecorator decorator = new QuestionDecorator();

    public List<Question> createQuestions(UrlList urlList) throws Exception {
        List<Question> list = new ArrayList<>();
        for (String it : urlList.getUrlList()) {
            List<String> stringList = fetcher.fetchData(it);
            List<String> strings = splitter.splitQuestions(stringList);
            HashMap<String,String>snippets = splitter.splitSnippets(splitter.splitHeader(stringList));
            list = parser.parseQuestionsWithAnswers(strings);
            list=decorator.addSnippetsToQuestions(snippets,list);
        }
        return list;
    }
}