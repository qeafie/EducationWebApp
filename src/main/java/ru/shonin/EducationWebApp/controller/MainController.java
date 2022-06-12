package ru.shonin.EducationWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.repository.UserRepository;
import ru.shonin.EducationWebApp.service.CategoryService;
import ru.shonin.EducationWebApp.service.QuizService;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    QuizService quizService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String welcome(Model model){

        //model.addAttribute("quizzes",this.quizService.getQuizes());
        return "greeting";
    }

    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("quizzes",this.quizService.getQuizes());
        model.addAttribute("categories",this.categoryService.getCategories());
        model.addAttribute("currentCategory","All");
        return "view-quizzes";
    }

    @GetMapping("/home/category/{categoryID}")
    public String home(Model model, @PathVariable Long categoryID){
        Category category = new Category();
        category.setId(categoryID);

        model.addAttribute("quizzes",this.quizService.getQuizzesOfCategory(category));
        model.addAttribute("categories",this.categoryService.getCategories());


        model.addAttribute("currentCategory",this.categoryService.getCategory(categoryID).getTitle());
        return "view-quizzes";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
