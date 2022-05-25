package ru.shonin.EducationWebApp.entity.testComponent;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @OneToMany(mappedBy = "task")
    private List<Answer> answers;

    @ManyToOne()
    @JoinColumn(name = "testId")
    private Test test;

    @OneToOne
    private Answer rightAnswer;
}
