package ru.shonin.EducationWebApp.entity.testComponent;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @OneToMany(mappedBy = "task")
    private List<Answer> answers;

    @Transient
    private Answer choose;
    @Transient
    String textChoose = "";

    public String getTextChoose() {
        return textChoose;
    }

    public void setTextChoose(String textChoose) {
        this.textChoose = textChoose;
    }

    @ManyToOne()
    @JoinColumn(name = "testId")
    private Test test;

    @OneToOne
    private Answer rightAnswer;

    public Task() {
    }

    public Task(Question question, List<Answer> answers, Answer rightAnswer) {
        this.question = question;
        question.setTask(this);
        this.answers = answers;
        for (Answer answer:answers
             ) {
            answer.setTask(this);
        }
        this.rightAnswer = rightAnswer;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Answer getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Answer rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Answer getChoose() {
        return choose;
    }

    public void setChoose(Answer choose) {
        this.choose = choose;
    }
}
