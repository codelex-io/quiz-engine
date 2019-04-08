package io.codelex.quiz.parser;

import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PojoCreator {
    private DataFetcher fetcher = new DataFetcher();
    private DataSplitter splitter = new DataSplitter();
    private DataValidator validator = new DataValidator();
    private DataParser parser = new DataParser();


    public List<Question> createQuestions(UrlList urlList) throws Exception {
        List<Question> list = new ArrayList<>();
        for (String it : urlList.getUrlList()) {
            List<String> stringList = fetcher.fetchData(it);
            List<String> strings = splitter.splitQuestions(stringList);
            list = parser.parseQuestionsWithAnswers(strings);
        }
        return list;
    }
}