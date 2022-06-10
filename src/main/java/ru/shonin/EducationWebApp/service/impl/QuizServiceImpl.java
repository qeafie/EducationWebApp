package ru.shonin.EducationWebApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.repository.newTestComponent.CategoryRepository;
import ru.shonin.EducationWebApp.repository.newTestComponent.QuizRepository;
import ru.shonin.EducationWebApp.service.QuizService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizes() {
        return new HashSet<>( this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        this.quizRepository.delete(quiz);
    }
}
