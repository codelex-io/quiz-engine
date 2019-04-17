package io.codelex.quiz.service;

import io.codelex.quiz.IQuizService;
import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Answer;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.parser.PojoCreator;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.MapQuestionRecordToQuestion;
import io.codelex.quiz.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class QuizService implements IQuizService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private PojoCreator pojoCreator;
    private MapQuestionRecordToQuestion toQuestion = new MapQuestionRecordToQuestion();
    private static final Logger LOG = LoggerFactory.getLogger(QuizService.class);
    
    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository, PojoCreator pojoCreator) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.pojoCreator = pojoCreator;
    }

    public List<Question> createQuestions(UrlList urlList) throws IOException {
        
        try {
            return pojoCreator.createQuestions(urlList);
        } catch (Exception e){
            LOG.warn("Bad repo link", e);
        }
        return Collections.emptyList();
    }

    public Question findQuestionById(Long id) {
        Optional<QuestionRecord> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return toQuestion.apply(question.get());
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

    public List<Question> randomTestQuestions(int questionCount) throws NoSuchElementException {
        return questionRepository.findRandomTestQuestions(questionCount).stream()
                .map(it -> toQuestion.apply(it))
                .collect(Collectors.toList());
    }

    @Transactional
    public QuestionRecord saveQuestion(Question question) {
        List<AnswerRecord> answerRecords = new ArrayList<>();

        QuestionRecord questionRecord = new QuestionRecord(
                question.getQuestion(),
                question.getCredits());
        questionRecord = questionRepository.save(questionRecord);
        for (Answer answer : question.getAnswerList()) {
            AnswerRecord record = saveAnswer(answer);
            answerRecords.add(record);
        }

        answerRecords.forEach(questionRecord::addAnswerRecord);
        return questionRepository.save(questionRecord);
    }

    private AnswerRecord saveAnswer(Answer answer) {

        AnswerRecord answerRecord = new AnswerRecord(
                answer.getAnswer(),
                answer.isCorrect()
        );
        answerRecord = answerRepository.save(answerRecord);
        return answerRecord;
    }

    public QuestionRecord testSaving(AddQuestionRequest request) {
        QuestionRecord questionRecord = new QuestionRecord(
                request.getQuestion(),
                request.getCredits());


        List<AnswerRecord> answerRecords = new ArrayList<>();


        List<AnswerRecord> answerRecordList = new ArrayList<>();
        request.getAnswers().forEach(it -> answerRecords.add(
                new AnswerRecord(it.getAnswer(), it.isCorrect())));


        questionRecord = questionRepository.save(questionRecord);

        for (AnswerRecord record : answerRecords) {
            AnswerRecord record1 = answerRepository.save(record);
            answerRecordList.add(record1);
        }
        answerRecordList.forEach(questionRecord::addAnswerRecord);
        return questionRepository.save(questionRecord);
    }
}
