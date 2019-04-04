package io.codelex.quiz.service;

import io.codelex.quiz.model.Answer;
import io.codelex.quiz.model.Question;
import io.codelex.quiz.repository.AnswerRepository;
import io.codelex.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class QuizService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;

    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }


    public Answer saveAnswer(@Valid Answer answer) {
        return answerRepository.save(answer);
    }

    public Question saveQuestion(Question question) {
        question.getAnswers()
                .forEach(it -> answerRepository.save(it));
        return questionRepository.save(question);
    }

    public Question findQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
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

    public List<Question> randomdTestQuestions(int questionCount) throws NoSuchElementException {
        if (questionCount <= 0) {
            return questionRepository.findRandomTestQuestions(questionCount);

        } else {
            throw new NoSuchElementException();
        }
    }
    
}
