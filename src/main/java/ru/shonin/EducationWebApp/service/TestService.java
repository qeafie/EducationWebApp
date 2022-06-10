package ru.shonin.EducationWebApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shonin.EducationWebApp.entity.testComponent.Task;
import ru.shonin.EducationWebApp.entity.testComponent.Test;
import ru.shonin.EducationWebApp.entity.testComponent.TestForm;
import ru.shonin.EducationWebApp.repository.testComponent.TaskRepository;
import ru.shonin.EducationWebApp.repository.testComponent.TestRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;
    @Autowired
    TaskRepository taskRepository;
    public TestForm getTestForm(Long testId){

        List<Task> taskList = (List<Task>) taskRepository.findAllByTestId(testId);
        Collections.shuffle(taskList);

        TestForm testForm = new TestForm();
        testForm.setTaskList(taskList);
        return testForm;
    }
}
