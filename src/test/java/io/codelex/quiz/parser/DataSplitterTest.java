package io.codelex.quiz.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DataSplitterTest {
    private DataFetcher dataFetcher = new DataFetcher();
    private DataSplitter splitter = new DataSplitter();
    private String url = "https://raw.githubusercontent.com/codelex-io/databases-quiz/master/sql/select/sql-zoo-02/QUIZME.md";
    private List<String> text = dataFetcher.fetchData(url);

    private String txt = "@snippet(id: 'tables')\n" +
            "something in here\n" +
            "@snippetEnd\n" +
            "@snippet(id: 'chairs')\n" +
            "smth else lul\n" +
            "@snippetEnd";

    public DataSplitterTest() throws Exception {
    }

    @Test
    public void should_get_credits() {
        String something = splitter.splitHeader(text);
        String credits = splitter.splitCredits(something);
        System.out.println(credits);
    }

    @Test
    public void testing_answer() {
        String header = splitter.splitHeader(text);
        HashMap<String, String> snippet = splitter.splitSnippets(header);
        System.out.println(snippet.get("tables"));
    }

    @Test
    public void should_handle_multiple_snippets() {
        HashMap<String, String> snippets = splitter.splitSnippets(txt);
        System.out.println(snippets.get("tables"));
        System.out.println(snippets.get("chairs"));
    }

}