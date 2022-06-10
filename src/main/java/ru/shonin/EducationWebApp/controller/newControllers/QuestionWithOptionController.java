package ru.shonin.EducationWebApp.controller.newControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.entity.testComponent.Question;
import ru.shonin.EducationWebApp.service.QuestionWithOptionService;
import ru.shonin.EducationWebApp.service.QuizService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/question")
public class QuestionWithOptionController {

    @Autowired
    private QuestionWithOptionService questionService;

    @Autowired
    private QuizService quizService;

    @GetMapping("/add")
    public String addQuestion(){

        return "add-question";
    }
    @PostMapping("/add")
    public String addQuestion(@RequestParam QuestionWithOption question){
        this.questionService.addQuestion(question);
        return "quizes";
    }

    @GetMapping("/{questionId}")
    public String getQuestion(@PathVariable("questionId") Long questionId,
                          Model model){
        if (this.questionService.getQuestion(questionId)!=null){
            model.addAttribute("quiz",this.questionService.getQuestion(questionId));
            return "question";
        }
        return "error500";
    }

    @GetMapping("/quiz/{quizId}")
    public String getQuestionOfQuiz(@PathVariable("quizId") Long quizID,
                                    Model model){

        Quiz quiz = this.quizService.getQuiz(quizID);
        Set<QuestionWithOption> questions = quiz.getQuestions();
        List list = new ArrayList(questions);
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestons())){
            list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestons()+1));
        }
        Collections.shuffle(list);
        model.addAttribute("Questions",list);
        return "questionOfQuiz";
    }
}
