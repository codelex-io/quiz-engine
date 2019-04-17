package io.codelex.quiz.service.pdfservice;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
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
                Collection<String> questionLine = new ArrayList<>();
                question.getQuestion().lines().forEach(questionLine::add);
                Phrase questionContent = new Phrase();
                List answerList = new List(List.ORDERED);
                
                
                

                int columnCount = 0;
                String[] cells;
                for (String line : questionLine) {
                    if(line.contains("|")) {
                        String[] cols = line.split("\\|");
                        columnCount = cols.length; //might need +1, lets see how it goes
                        
                    }
                }
                PdfPTable snippetTable = new PdfPTable(columnCount);
//                snippetTable.addCell();
                questionContent.add(snippetTable);
                
                
                
                
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
