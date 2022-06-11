package ru.shonin.EducationWebApp.entity.newTestComponent;


import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;


    private  String title;

    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    public Category() {
    }

    public Category( String title, String description) {
        this.title = title;
        this.description = description;
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

    public Category getCategory(){
        return this;
    }

    public void addQuiz(Quiz quiz){
        this.quizzes.add(quiz);
    }
}
