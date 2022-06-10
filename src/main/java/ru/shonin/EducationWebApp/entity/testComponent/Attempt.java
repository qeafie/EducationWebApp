package ru.shonin.EducationWebApp.entity.testComponent;

import ru.shonin.EducationWebApp.entity.User;

import javax.persistence.*;

@Entity
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "test_id")
//    private Test test;

    private int score;

    public void setId(Long attemptId) {
        this.id = attemptId;
    }

    @Id
    public Long getId() {
        return id;
    }

    public Attempt() {
    }

    public Attempt(User user, Test test, int score) {
        this.user = user;
        //this.test = test;
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Test getTest() {
//        return test;
//    }

//    public void setTest(Test test) {
//        this.test = test;
//    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
