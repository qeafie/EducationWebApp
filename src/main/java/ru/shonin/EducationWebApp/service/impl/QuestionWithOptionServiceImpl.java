package ru.shonin.EducationWebApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.entity.testComponent.Question;
import ru.shonin.EducationWebApp.repository.newTestComponent.QuestionWithOptionRepository;
import ru.shonin.EducationWebApp.service.QuestionWithOptionService;

import java.util.*;

@Service
public class QuestionWithOptionServiceImpl implements QuestionWithOptionService {

    @Autowired
    QuestionWithOptionRepository questionRepository;
    @Override
    public QuestionWithOption addQuestion(QuestionWithOption question) {
        return this.questionRepository.save(question);
    }

    @Override
    public QuestionWithOption updateQuestion(QuestionWithOption question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<QuestionWithOption> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public QuestionWithOption getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<QuestionWithOption> getQuestionOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long idQuestion) {
        QuestionWithOption question = new QuestionWithOption();
        question.setId(idQuestion);
        this.questionRepository.delete(question);
    }

    @Override
    public List<QuestionWithOption> getShuffleQuestionOfQuiz(Quiz quiz) {

        List<QuestionWithOption> list = new ArrayList<>(this.questionRepository.findByQuiz(quiz));
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        Collections.shuffle(list);

        return list;
    }
}
