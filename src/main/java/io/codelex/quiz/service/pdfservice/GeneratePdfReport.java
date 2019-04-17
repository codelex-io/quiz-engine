package io.codelex.quiz.service.pdfservice;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfTable;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                Collection<String> questionLine = new ArrayList<>();
                String stringFrom = question.getQuestion();
                question.getQuestion().lines().forEach(questionLine::add);
                Phrase questionContent = new Phrase();
                List answerList = new List(List.ORDERED);
                for (String line : questionLine) {
                    if (StringUtils.countMatches(line, "|") > 1) {
                        int columnCount = StringUtils.countMatches(line, "|") - 1;
                        PdfPTable snippetTable = new PdfPTable(columnCount);
                        Pattern idPattern = Pattern.compile("\\|+\\w+\\s+\\|");
                        Matcher idMatcher = idPattern.matcher(line);
                        while (idMatcher.find()) {
                            snippetTable.addCell(idMatcher.group());
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
