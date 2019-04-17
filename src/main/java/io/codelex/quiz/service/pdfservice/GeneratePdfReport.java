package io.codelex.quiz.service.pdfservice;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GeneratePdfReport {

    public static ByteArrayInputStream quizInputStream(Collection<Question> questionList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            int questionIndex = 1;
            for (Question question : questionList) {
                Phrase questionContent = new Phrase();
                List answerList = new List(List.ORDERED);
                Collection<String> questionLine = new ArrayList<>();
                question.getQuestion().lines().forEach(questionLine::add);
                for (String line : questionLine) {
                    if (StringUtils.countMatches(line, "|") > 1) {
                        int columnCount = StringUtils.countMatches(line, "|") - 1;
                        PdfPTable snippetTable = new PdfPTable(columnCount);
                        Pattern idPattern = Pattern.compile("(?<=\\|)([^|]+)(?=\\|)");
                        Matcher idMatcher = idPattern.matcher(line);
                        while (idMatcher.find()) {
                            if (!idMatcher.group(1).contains("--")) {
                                snippetTable.addCell(idMatcher.group(1));
                                System.out.println(idMatcher.end());
                            }
                        }
                        
                        questionContent.add(snippetTable);
                    }
                }

                questionContent.add(questionIndex++ + ") " + question.getQuestion());
                for (Answer answer : question.getAnswerList()) {
                    answerList.add(new ListItem(answer.getAnswer()));
                }
                questionContent.add(answerList);
                questionContent.add(question.getCredits());
                document.add(questionContent);
            }

            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}