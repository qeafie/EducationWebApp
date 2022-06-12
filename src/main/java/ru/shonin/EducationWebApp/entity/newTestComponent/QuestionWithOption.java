package ru.shonin.EducationWebApp.entity.newTestComponent;

import javax.persistence.*;

@Entity
public class QuestionWithOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String answer;


    @Transient
    private String formAnswer;

    public String getFormAnswer() {
        return formAnswer;
    }

    public void setFormAnswer(String formAnswer) {
        this.formAnswer = formAnswer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private  Quiz quiz;

    public QuestionWithOption() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getAnswerByNumber(Long answerNumber) {
        if (answerNumber==1) return option1;
        if (answerNumber==2) return option2;
        if (answerNumber==3) return option3;
        if (answerNumber==4) return option4;

        return "";
    }
}
