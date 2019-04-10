package io.codelex.quiz.parser;

import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataParserTest {
    private DataParser parser = new DataParser();
    @Test
    public void should_create_question_object_with_1_incorrect_answer_and_1_correct() {
        //given
        List<String> stringList = createStringList();
        Question question = new Question(
                "@insertSnippet('tables')" +
                "Select the code which produces this table:" +
                "| name        | population |" +
                "|-------------|------------|" +
                "| Bahrain     | 1234571    |" +
                "| Swaziland   | 1220000    |" +
                "| Timor-Leste | 1066409    |" +
                "| ...         |            |");

        Answer answer1 = new Answer(
                "sql FROM world SELECT name, population BETWEEN 1000000 AND 1250000",
                false);
        Answer answer2 = new Answer(
                "sql SELECT name, population FROM world WHERE population BETWEEN 1000000 AND 1250000",
                true);
        //when
        List<Question> questions = parser.parseQuestionsWithAnswers(stringList);

        //then
        System.out.println(questions.get(0).getAnswerList().get(0));
        assertEquals(question.getQuestion(),questions.get(0).getQuestion());
        assertEquals(questions.get(0).getAnswerList().get(0).getAnswer(), answer1.getAnswer());
        assertEquals(questions.get(0).getAnswerList().get(0).isCorrect(), answer1.isCorrect());
        assertEquals(questions.get(0).getAnswerList().get(1).getAnswer(), answer2.getAnswer());
        assertEquals(questions.get(0).getAnswerList().get(1).isCorrect(), answer2.isCorrect());


    }
    
    @Test
    public void should_create_question_object_with_answers(){
        
    }

    private List<String> createStringList() {
        List<String> stringList = new ArrayList<>();
        stringList.add(
                
                "@insertSnippet('tables')\n" +
                "\nSelect the code which produces this table:\n" +
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

