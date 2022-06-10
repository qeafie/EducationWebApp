package ru.shonin.EducationWebApp.entity.testComponent;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    public Answer() {
    }

    public Answer(String text) {
        this.text = text;
    }

    public Answer(String text, Task task) {
        this.text = text;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
