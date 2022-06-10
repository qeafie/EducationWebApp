package ru.shonin.EducationWebApp.controller.newControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/add")
    public String addQuiz(){

        return "add-quiz";
    }
    @PostMapping("/add")
    public String addQuiz(@RequestParam Quiz quiz){
        this.quizService.addQuiz(quiz);
        return "quizes";
    }

    @GetMapping("/{quizId}")
    public String getQuiz(@PathVariable("quizId") Long quizId,
                              Model model){
        if (this.quizService.getQuiz(quizId)!=null){
            model.addAttribute("quiz",this.quizService.getQuiz(quizId));
            return "quiz";
        }
        return "error500";
    }
}
