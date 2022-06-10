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
import ru.shonin.EducationWebApp.entity.testComponent.Test;
import ru.shonin.EducationWebApp.repository.testComponent.AnswerRepository;
import ru.shonin.EducationWebApp.repository.testComponent.QuestionRepository;
import ru.shonin.EducationWebApp.repository.testComponent.TaskRepository;
import ru.shonin.EducationWebApp.repository.testComponent.TestRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

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

    @GetMapping("/test/{idTest}/task/add")
    public String taskAdd(@PathVariable(value = "idTest") Long idTest,
                           Model model
    ){
        model.addAttribute("test",testRepository.findById(idTest).get());
        return "task-add-page-1";
    }
    @PostMapping("/test/{idTest}/task/add")
    public String taskAdd(@PathVariable(value = "idTest") Long idTest,
                          Model model,
                          @RequestParam String questionText,
                          @RequestParam int numberOfRight,
                           @RequestParam String answer1,
                           @RequestParam String answer2,
                           @RequestParam String answer3,
                           @RequestParam String answer4
                          //@RequestParam Long quantity
                          ){




        Question question = new Question(questionText);
        questionRepository.save(question);
        Answer a1 =new Answer(answer1);
        Answer a2 =new Answer(answer1);
        Answer a3 =new Answer(answer1);
        Answer a4 =new Answer(answer1);


        List<Answer> list = new ArrayList<Answer>(List.of(a1,a2,a3,a4));
        answerRepository.saveAll(list);
        Test test = testRepository.findById(idTest).get();
        Task task = new Task(question,list,list.get(numberOfRight-1));
        taskRepository.save(task);
        task.setTest(test);
        test.getTasks().add(task);
        testRepository.save(test);

        return task(idTest,model);
    }

//    @GetMapping("/test/{idTest}/task/add-page-2")
//    public String taskAdd2(@PathVariable(value = "idTest") Long idTest,
//                          Model model
//    ){
//        Task task = new Task();
//        model.addAttribute("task", task);
//
//        return "task-add-page-2";
//    }

//    @PostMapping("/test/{idTest}/task/add-page-2")
//    public String taskAdd2(@PathVariable(value = "idTest") Long idTest,
//                           Model model,
//                           @RequestParam Long number,
//                           @RequestParam String answer1,
//                           @RequestParam String answer2,
//                           @RequestParam String answer3,
//                           @RequestParam String answer4
//                           ){
//
//        if (!testRepository.findById(idTest).isPresent()){
//            return "redirect:/Error500";
//        }
//        Test test = testRepository.findById(idTest).get();
//        Question question = (Question) model.getAttribute("question");
//        System.out.println(question);
//        //Task task = new Task();
//
//        return "tasks";
//    }

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
