package ru.shonin.EducationWebApp.entity.newTestComponent;


import ru.shonin.EducationWebApp.entity.Attempt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Quiz {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String title;
    private String description;
    private int maxMarks;
    private int numberOfQuestions;



    private boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany( mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<QuestionWithOption> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attempt> attempts = new ArrayList<>();

    public Quiz() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }

    public int getNumberOfQuestions() {
        return this.questions.size();
    }

    public void setNumberOfQuestions(int numberOfQuestons) {
        this.numberOfQuestions = numberOfQuestons;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<QuestionWithOption> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionWithOption> questions) {
        this.questions = questions;
    }

    public void addQuestion(QuestionWithOption question){
        this.questions.add(question);
    }

    public int getScoresForQuestion(){
        return maxMarks/questions.size();
    }

//    public List<Attempt> getAttempts() {
//        return attempts;
//    }
//
//    public void setAttempts(List<Attempt> attempts) {
//        this.attempts = attempts;
//    }
}
