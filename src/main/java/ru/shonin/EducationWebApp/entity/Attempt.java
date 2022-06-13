package ru.shonin.EducationWebApp.entity;

import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

import javax.persistence.*;

@Entity
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private User user;


    private double result;

    public Attempt() {
    }

    public Attempt(Long id, Quiz quiz, User user, double result) {
        this.id = id;
        this.quiz = quiz;
        this.user = user;
        this.result = result;
    }

    public Attempt(Quiz quiz, User user, double result) {
        this.quiz = quiz;
        this.user = user;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }


}
