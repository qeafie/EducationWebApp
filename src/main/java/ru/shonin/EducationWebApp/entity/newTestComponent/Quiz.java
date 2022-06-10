package ru.shonin.EducationWebApp.entity.newTestComponent;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String title;
    private String description;
    private String maxMarks;
    private String numberOfQuestons;

    private boolean active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<QuestionWithOption> questions =new HashSet<>();

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

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumberOfQuestons() {
        return numberOfQuestons;
    }

    public void setNumberOfQuestons(String numberOfQuestons) {
        this.numberOfQuestons = numberOfQuestons;
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
}
