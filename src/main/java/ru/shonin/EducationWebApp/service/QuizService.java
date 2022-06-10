package ru.shonin.EducationWebApp.service;

import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

import java.util.Set;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quizuiz);
    public Set<Quiz> getQuizes();
    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);
}
