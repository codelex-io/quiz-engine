package io.codelex.quiz.service;

import io.codelex.quiz.api.AddAnswerRequest;
import io.codelex.quiz.api.AddQuestionRequest;
import io.codelex.quiz.api.Question;
import io.codelex.quiz.api.UrlList;
import io.codelex.quiz.model.AnswerRecord;
import io.codelex.quiz.model.QuestionRecord;
import io.codelex.quiz.parser.PojoCreator;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.MapAddQuestionToQuestion;
import io.codelex.quiz.repository.MapQuestionRecordToQuestion;
import io.codelex.quiz.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QuizServiceImpl implements io.codelex.quiz.QuizService {
    private static final Logger LOG = LoggerFactory.getLogger(QuizServiceImpl.class);
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private PojoCreator pojoCreator;
    private MapAddQuestionToQuestion mapAddQuestionToQuestion = new MapAddQuestionToQuestion();
    private MapQuestionRecordToQuestion toQuestion = new MapQuestionRecordToQuestion();

    public QuizServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository, PojoCreator pojoCreator) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.pojoCreator = pojoCreator;
    }

    public List<AddQuestionRequest> createQuestions(UrlList urlList) {
        try {
            return pojoCreator.createQuestions(urlList);
        } catch (Exception e) {
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
    public Question saveQuestion(AddQuestionRequest question) {
        List<AnswerRecord> answerRecords = new ArrayList<>();

        QuestionRecord questionRecord = new QuestionRecord(
                question.getQuestion(),
                question.getCredits());
        questionRecord = questionRepository.save(questionRecord);
        for (AddAnswerRequest answer : question.getAnswers()) {
            AnswerRecord record = saveAnswer(answer);
            answerRecords.add(record);
        }

        answerRecords.forEach(questionRecord::addAnswerRecord);
        return toQuestion.apply(questionRepository.save(questionRecord));
    }

    private AnswerRecord saveAnswer(AddAnswerRequest answer) {

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