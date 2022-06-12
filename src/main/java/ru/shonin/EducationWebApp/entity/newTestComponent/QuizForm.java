package ru.shonin.EducationWebApp.entity.newTestComponent;

import java.util.List;

public class QuizForm {
    private List<QuestionWithOption> questions;

    public void addQuestion(QuestionWithOption question){
        this.questions.add(question);
    }

    public QuizForm() {
    }

    public QuizForm(List<QuestionWithOption> questions) {
        this.questions = questions;
    }

    public List<QuestionWithOption> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionWithOption> questions) {
        this.questions = questions;
    }
}
