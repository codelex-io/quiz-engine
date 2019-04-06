package io.codelex.quiz.parser;

import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DataParserTest {
    DataFetcher dataFetcher = new DataFetcher();
    DataSplitter splitter = new DataSplitter();
    DataParser parser = new DataParser();
    String url = "https://raw.githubusercontent.com/codelex-io/databases-quiz/master/sql/select/sql-zoo-02/QUIZME.md";
    List<String> data = dataFetcher.fetchData(url);

    public DataParserTest() throws Exception {
    }

    @Test
    public void testing_the_shit() {
        //given
        List<String> rawQuestions = splitter.splitQuestions(data);
        
        //when
        List<Question> questions = parser.parseQuestions(rawQuestions);
        
        //then
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestion());
            for (Answer answer : questions.get(i).getAnswers()) {
                System.out.println(answer.getAnswer());
            }
            System.out.println("-------------------------------------------------------");
        }
    }
    
    
    
    
    @Test
    public void should_be_able_to_divide_questions_into_question_objects() {
        
    }
    
    @Test
    public void should_get_credits_out_of_passed_text_file() {
        
    }
    
    @Test
    public void should_get_snippets_out_of_passed_text_file() {
        
    }
}