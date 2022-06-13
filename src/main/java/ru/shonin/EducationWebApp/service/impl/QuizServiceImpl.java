package ru.shonin.EducationWebApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuizForm;
import ru.shonin.EducationWebApp.repository.newTestComponent.CategoryRepository;
import ru.shonin.EducationWebApp.repository.newTestComponent.QuizRepository;
import ru.shonin.EducationWebApp.service.QuizService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
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

    @Override
    public Set<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);
    }

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategoryAndActive(category,true);
    }

    @Override
    public double getResult(Quiz quiz, QuizForm quizForm) {
        Set<QuestionWithOption> questions = quiz.getQuestions();
        List<QuestionWithOption> quizFormQuestions = quizForm.getQuestions();
        double count = 0;
        final double STEP = quiz.getScoresForQuestion();
        for (QuestionWithOption question : quizFormQuestions){
            for (QuestionWithOption questionWithAnswer : questions){

                if(question.getId().equals( questionWithAnswer.getId())){
                    if(question.getFormAnswer()!=null&&
                            question.getFormAnswer().equals(questionWithAnswer.getAnswer()))
                        count+=STEP;
                }
            }
        }

        return count;
    }
}
