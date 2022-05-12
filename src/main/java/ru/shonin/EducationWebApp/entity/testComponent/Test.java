package ru.shonin.EducationWebApp.entity.testComponent;

import lombok.Data;

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


}
