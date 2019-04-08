package io.codelex.quiz.service;

import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.parser.PojoCreator;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class QuizService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private PojoCreator pojoCreator;

    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository, PojoCreator pojoCreator) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.pojoCreator = pojoCreator;
    }

    public List<Question> createQuestions(UrlList urlList)throws Exception {
        return pojoCreator.createQuestions(urlList);
    }
    
    public AnswerRecord saveAnswer(@Valid AnswerRecord answerRecord) {
        return answerRepository.save(answerRecord);
    }

    public QuestionRecord findQuestionById(Long id) {
        Optional<QuestionRecord> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    public void deleteAnswerById(Long id) throws IllegalArgumentException {
        answerRepository.deleteById(id);
    }

    public void deleteQuestionById(Long id) throws IllegalArgumentException {
        questionRepository.deleteById(id);
    }

    public List<QuestionRecord> randomdTestQuestions(int questionCount) throws NoSuchElementException {
        if (questionCount <= 0) {
            return questionRepository.findRandomTestQuestions(questionCount);

        } else {
            throw new NoSuchElementException();
        }
    }

    public QuestionRecord saveQuestion(QuestionRecord questionRecord) {
        questionRecord.getAnswerRecords()
                .forEach(it -> answerRepository.save(it));
        return questionRepository.save(questionRecord);
    }

    public QuestionRecord testSaving(AddQuestionRequest request) {
        List<AnswerRecord> answerRecordList = new ArrayList<>();
        QuestionRecord questionRecord = new QuestionRecord(request.getQuestion(),request.getCredits(), answerRecordList);
        request.getAnswers()
                .forEach(it -> answerRecordList.add(new AnswerRecord(questionRecord, it.getAnswer(), it.isCorrect())));

//        List<AnswerRecord> answerRecordList = new ArrayList<>();
//        request.getAnswerRecords()
//                .forEach(it->answerRecordList.add(new AnswerRecord(it.getAnswer(),it.isCorrect())));
//        QuestionRecord questionRecord = new QuestionRecord(request.getQuestionRecord(),request.getCredits());
        for (AnswerRecord answerRecord : answerRecordList) {
            saveAnswer(answerRecord);
        }
/*
        questionRecord.setAnswerRecords(answerRecordList);
*/
        return saveQuestion(questionRecord);
    }
}
