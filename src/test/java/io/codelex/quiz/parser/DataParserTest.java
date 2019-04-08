package io.codelex.quiz.parser;

import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataParserTest {
    private DataFetcher dataFetcher = new DataFetcher();
    private DataSplitter splitter = new DataSplitter();
    private DataParser parser = new DataParser();
    private String url = "https://raw.githubusercontent.com/codelex-io/databases-quiz/master/sql/select/sql-zoo-02/QUIZME.md";
    private List<String> data = dataFetcher.fetchData(url);

    public DataParserTest() throws Exception {
    }

    @Test
    public void testing_the_shit() {
        //given
       List<String> rawQuestions = splitter.splitQuestions(data);

        //when
        List<Question> questions = parser.parseQuestionsWithAnswers(rawQuestions);

        //then
        for (int i = 0; i < questions.size(); i++) {
            System.out.println(questions.get(i).getQuestion());
            for (Answer answer : questions.get(i).getAnswerList()) {
                System.out.println(answer.getAnswer());
            }
            System.out.println("-------------------------------------------------------");
        }
    }


    @Test
    public void should_create_question_object_with_1_incorrect_answer_and_1_correct() {
        //given
        List<String> stringList = createStringList();
        Question question = new Question(
                "Select the code which produces this table:\n" +
                "\n" +
                "| name        | population |\n" +
                "|-------------|------------|\n" +
                "| Bahrain     | 1234571    |\n" +
                "| Swaziland   | 1220000    |\n" +
                "| Timor-Leste | 1066409    |\n" +
                "| ...         |            |\n" +
                "\n");

        Answer answer1 = new Answer(
                "sql FROM world SELECT name, population BETWEEN 1000000 AND 1250000",
                false);
        Answer answer2 = new Answer(
                "sql SELECT name, population FROM world WHERE population BETWEEN 1000000 AND 1250000",
                true);
        //when
        List<Question> questions = parser.parseQuestionsWithAnswers(stringList);

        //then
        assertEquals(questions.get(0).getQuestion(), question.getQuestion());
        assertEquals(questions.get(0).getAnswerList().get(0).getAnswer(), answer1.getAnswer());
        assertEquals(questions.get(0).getAnswerList().get(0).isItCorrect(), answer1.isItCorrect());
        assertEquals(questions.get(0).getAnswerList().get(1).getAnswer(), answer2.getAnswer());
        assertEquals(questions.get(0).getAnswerList().get(1).isItCorrect(), answer2.isItCorrect());


    }

    @Test
    public void should_get_credits_out_of_passed_text_file() {

    }

    @Test
    public void should_get_snippets_out_of_passed_text_file() {

    }

    private List<String> createStringList() {
        List<String> stringList = new ArrayList<>();
        stringList.add(
                "Select the code which produces this table:\n" +
                "\n" +
                "| name        | population |\n" +
                "|-------------|------------|\n" +
                "| Bahrain     | 1234571    |\n" +
                "| Swaziland   | 1220000    |\n" +
                "| Timor-Leste | 1066409    |\n" +
                "| ...         |            |\n" +
                "\n" +
                "a)\n" +
                "```sql\n" +
                "FROM world SELECT name, population BETWEEN 1000000 AND 1250000\n" +
                "```\n" +
                "\n" +
                "b*)```sql\n" +
                "SELECT name, population FROM world WHERE population BETWEEN 1000000 AND 1250000\n" +
                "```\n");
        return stringList;
    }
}

