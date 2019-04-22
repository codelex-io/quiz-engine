package io.codelex.quiz.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Table;
import io.codelex.quiz.api.AddAnswerRequest;
import io.codelex.quiz.api.AddQuestionRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GeneratePdfReport {

    public static ByteArrayInputStream quizInputStream(Collection<AddQuestionRequest> questionList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            for (AddQuestionRequest question : questionList) {
                Paragraph questionContent = createQuestionAndAnswerParagraph(question);
                document.add(questionContent);
            }
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static Paragraph createQuestionAndAnswerParagraph(AddQuestionRequest question) {
        int questionIndex = 1;
        Font regular = new Font(Font.FontFamily.TIMES_ROMAN, 10);
        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC);
        Font snippet = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.ITALIC);
        Paragraph questionContent = new Paragraph();
        questionContent.setSpacingAfter(5);
        questionContent.setSpacingBefore(5);
        questionContent.add(new Chunk(questionIndex++ + ") ",regular));
        String[] splittingHeader = splittingHeader(question.getQuestion());
        questionContent.add(new Chunk(splittingHeader[0].trim() + "\n", bold));
        questionContent.add(new Chunk(splittingHeader[2].trim(), regular));
        List answerList = new List(List.ORDERED);
        for (AddAnswerRequest answer : question.getAnswers()) {
            if (addingSQLSnipetts(answer.getAnswer()) != null) {
                answerList.add(new ListItem(addingSQLSnipetts(answer.getAnswer()).trim() + "\n", snippet));
            }
            try {
                Table table = creatingTable(answer.getAnswer());
            
            if(table!=null){
                answerList.add(new ListItem(new Chunk()));
            }else {
                answerList.add(new ListItem(answer.getAnswer().trim() + "\n", regular));
            }
            } catch (BadElementException e) {
                answerList.add(new ListItem(answer.getAnswer().trim() + "\n", regular));
            }
        }

        questionContent.add(answerList);
        questionContent.add(question.getCredits());
        return questionContent;
    }

    private static String[] splittingHeader(String string) {
        String[] splittedTextBetweenTags = string.split("\\*\\*", 3);
        splittedTextBetweenTags[0] = StringUtils.substringBetween(string, "**", "**").trim();
        return splittedTextBetweenTags;
    }

    private static String addingSQLSnipetts(String string) {
        return StringUtils.substringBetween(string, "```sql", "```");
    }
    
    private static int extractingColumnCount(String string) {
        String[] answerSplittedBetweenLines = string.split("\n");
        for (String line : answerSplittedBetweenLines) {
            if (line.contains("|")) {
                return StringUtils.countMatches(line, "|") - 1;
            }
        }

        return 0;
    }

    private static Table creatingTable(String string) throws com.lowagie.text.BadElementException {
        if (StringUtils.substringBetween(string, "```", "```") == null) {
            return null;
        } else {
            int tableColumns = extractingColumnCount(string);
            Table table = new Table(tableColumns);
            final Pattern pattern = Pattern.compile("(?<=\\|)[^|]++(?=\\|)");
            final Matcher matcher = pattern.matcher(string);
            while (matcher.find()) {
                if (!matcher.group().contains("+-")) {
                    table.addCell(new Cell(matcher.group()));
                }
                System.out.println(matcher.group());
            }
            return null;
        }
    }

    private static String insertingSqlSnippetsInQuestions(String string) {

        // Pattern patt = Pattern.compile("(?<=\\*\\*).*?(?=\\*\\*)"); patter for checking headers
        // Pattern patternForSqlSnippets = Pattern.compile("(?<=)```sql.*?(?=```)");
        Pattern patternForSqlSnippets = Pattern.compile("(?<=```sql).*?(?=```)");
        Matcher matcher = patternForSqlSnippets.matcher(string);
        StringBuffer sb = new StringBuffer(string.length());
        while (matcher.find()) {
            System.out.println(matcher.group(0));
           /* String text = m.group(0);
            text="<b>"+text+"</b>";
            m.appendReplacement(sb, Matcher.quoteReplacement(text));*/
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}