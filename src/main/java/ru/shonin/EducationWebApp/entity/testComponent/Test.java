package ru.shonin.EducationWebApp.entity.testComponent;

import lombok.Data;
import ru.shonin.EducationWebApp.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String title;
    private  String description;

    private String image;

    @OneToMany(mappedBy = "test")
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

//    @OneToMany(mappedBy = "test")
//    private List<Attempt> attempts;

    public String getAuthorName(){
        return author!=null?author.getLogin():"<none>";
    }

    public Test(String title, String description, String image,  User author) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.author = author;
    }

    public Test() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

//    public void setAttempts(List<Attempt> attempts) {
//        this.attempts = attempts;
//    }

//    public void addAttempt(Attempt attempt){
//        attempts.add(attempt);
//        attempt.setTest(this);
//    }
}
