package ru.shonin.EducationWebApp.entity.testComponent;

import lombok.Data;
import ru.shonin.EducationWebApp.entity.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String title;
    private  String description;

    @OneToMany(mappedBy = "test")
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName(){
        return author!=null?author.getLogin():"<none>";
    }

    public Test(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public Test() {

    }
}
