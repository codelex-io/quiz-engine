package io.codelex.quiz.service;

import com.qkyrie.markdown2pdf.Markdown2PdfConverter;
import com.qkyrie.markdown2pdf.internal.exceptions.ConversionException;
import com.qkyrie.markdown2pdf.internal.exceptions.Markdown2PdfLogicException;
import com.qkyrie.markdown2pdf.internal.reading.SimpleStringMarkdown2PdfReader;
import com.qkyrie.markdown2pdf.internal.writing.SimpleFileMarkdown2PdfWriter;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.Quiz;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PdfCreator {


    public byte[] createPdf(List<Question> list) throws Markdown2PdfLogicException, ConversionException {
        Quiz quiz = new Quiz(list);
        Markdown2PdfConverter.newConverter()
                .readFrom(new SimpleStringMarkdown2PdfReader(quiz.toString()))
                .writeTo(new SimpleFileMarkdown2PdfWriter(new File("/home/arnolds/IdeaProjects/quiz-engine/src/main/resources/boomshakalaka"))).doIt();
        try {
            File file = ResourceUtils.getFile("/home/arnolds/IdeaProjects/quiz-engine/src/main/resources/boomshakalaka");
            return IOUtils.toByteArray(new FileInputStream(file));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR");
            return null;

        }
    }


}