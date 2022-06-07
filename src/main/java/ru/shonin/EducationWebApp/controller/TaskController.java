package ru.shonin.EducationWebApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shonin.EducationWebApp.entity.testComponent.Answer;
import ru.shonin.EducationWebApp.entity.testComponent.Question;
import ru.shonin.EducationWebApp.entity.testComponent.Task;
import ru.shonin.EducationWebApp.repository.testComponent.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/test/{idTest}/task/{idTask}")
    public String task(@PathVariable(value = "idTest") Long idTest,
                       @PathVariable(value = "idTask") Long idTask,
                       Model model
    ){
        Task task = taskRepository.findById(idTask).get();
        model.addAttribute("task", task);
        return "task";
    }

    @GetMapping("/test/{idTest}/tasks")
    public String task(@PathVariable (value = "idTest") Long idTest,
                       Model model){
        model.addAttribute("tasks",taskRepository.findAllByTestId(idTest));
        return "tasks";
    }

    @GetMapping("/test/{idTest}/task/{idTask}/add")
    public String taskAdd(@PathVariable(value = "idTest") Long idTest,
                           @PathVariable(value = "idTask") Long idTask,
                           Model model
    ){
        Task task = taskRepository.findById(idTask).get();
        model.addAttribute("task", task);
        return "task-add-page-1";
    }
    @PostMapping("/test/{idTest}/task/{idTask}/add")
    public String taskAdd(@PathVariable(value = "idTest") Long idTest,
                          @PathVariable(value = "idTask") Long idTask,
                          Model model,
                          @RequestParam String question,
                          @RequestParam int quantity){

        Question question1 = new Question();
        question1.setText(question);
        model.addAttribute("question",question1);
        List<Answer> list= new ArrayList<>();
        for (int i=0;i<quantity;i++)
            list.add(new Answer());
        model.addAttribute("answers",list);
        return "task-add-page-2"
    }

    @GetMapping("/test/{idTest}/task/{idTask}/add-page-2")
    public String taskAdd2(@PathVariable(value = "idTest") Long idTest,
                          @PathVariable(value = "idTask") Long idTask,
                          Model model
    ){
        Task task = taskRepository.findById(idTask).get();
        model.addAttribute("task", task);
        return "task-add-page-2";
    }


    @GetMapping("/test/{idTest}/task/{idTask}/edit")
    public String taskEdit(@PathVariable(value = "idTest") Long idTest,
                       @PathVariable(value = "idTask") Long idTask,
                       Model model
    ){
        Task task = taskRepository.findById(idTask).get();
        model.addAttribute("task", task);
        return "task-edit";
    }

    @PostMapping("/test/{idTest}/task/{idTask}/edit")
    public String taskEdit(@PathVariable String idTask,
                           @PathVariable String idTest){

        return "task";
    }
}
