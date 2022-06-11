package ru.shonin.EducationWebApp.controller.newControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.service.CategoryService;
import ru.shonin.EducationWebApp.service.QuizService;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/all")
    public String getAllQuiz(Model model){

        model.addAttribute("quizzes",this.quizService.getQuizes());
        return "quizzes";
    }
    @GetMapping("/add")
    public String addQuiz(Model model){
        Quiz quiz = new Quiz();
        model.addAttribute("quiz",quiz);
        model.addAttribute("categories",this.categoryService.getCategories());
        return "add-quiz";
    }
    @PostMapping("/add")
    public String addQuiz(@ModelAttribute Quiz quiz,@RequestParam Long categoryId){
        Category category = this.categoryService.getCategory(categoryId);
        quiz.setCategory(category);
        category.addQuiz(this.quizService.addQuiz(quiz));
        return "redirect:/quiz/all";
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
