package ru.shonin.EducationWebApp.service;

import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.entity.testComponent.Question;

import java.util.List;
import java.util.Set;

public interface QuestionWithOptionService {
    public QuestionWithOption addQuestion(QuestionWithOption question);
    public QuestionWithOption updateQuestion(QuestionWithOption question);
    public Set<QuestionWithOption> getQuestions();
    public QuestionWithOption getQuestion(Long questionId);
    public Set<QuestionWithOption> getQuestionOfQuiz(Quiz quiz);
    public void deleteQuestion(Long idQuestion);

    public List<QuestionWithOption> getShuffleQuestionOfQuiz(Quiz quiz);
}
