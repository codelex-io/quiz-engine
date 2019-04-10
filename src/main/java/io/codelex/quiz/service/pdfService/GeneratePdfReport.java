package io.codelex.quiz.service.pdfService;

import com.itextpdf.text.*;

import com.itextpdf.text.pdf.PdfWriter;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import com.itextpdf.text.List;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class GeneratePdfReport {

    public static ByteArrayInputStream quizInputStream(ArrayList<Question> questionList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            int questionIndex = 1;
            for (Question question : questionList) {
                Phrase questionContent = new Phrase();
                List answerList = new List(List.ORDERED);
                
                questionContent.add(questionIndex++ +") " +question.getQuestion());
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
