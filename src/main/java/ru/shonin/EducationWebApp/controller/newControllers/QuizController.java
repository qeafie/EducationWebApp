package ru.shonin.EducationWebApp.controller.newControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuizForm;
import ru.shonin.EducationWebApp.service.CategoryService;
import ru.shonin.EducationWebApp.service.QuestionWithOptionService;
import ru.shonin.EducationWebApp.service.QuizService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionWithOptionService questionService;


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

    @GetMapping("/active")
    public String getActiveQuiz (Model model){
        model.addAttribute("quizzes",this.quizService.getActiveQuizzes());
        model.addAttribute("categories",this.categoryService.getCategories());
        model.addAttribute("currentCategory","All");
        return "view-quizzes";
    }

    @GetMapping("/category/active/{categoryID}")
    public String getActiveAndCategoryQuiz(Model model, @PathVariable Long categoryID){
        Category category = new Category();
        category.setId(categoryID);

        model.addAttribute("quizzes",this.quizService.getActiveQuizzesOfCategory(category));
        model.addAttribute("categories",this.categoryService.getCategories());

        model.addAttribute("currentCategory",this.categoryService.getCategory(categoryID).getTitle());
        return "view-quizzes";
    }

    @GetMapping("/instruction/{quizId}")
    public String getInstruction(Model model,
                                 @PathVariable Long quizId){
        model.addAttribute("quiz",this.quizService.getQuiz(quizId));
        return "view-instuction";
    }


    @GetMapping("/{quizId}/start")
    public String start(@PathVariable Long quizId,
                        Model model){
        Quiz quiz = this.quizService.getQuiz(quizId);
        QuizForm quizForm = new QuizForm();

        List<QuestionWithOption> questions = new ArrayList<>(questionService.getShuffleQuestionOfQuiz(quiz));

        //model.addAttribute("quizList",questionService.getShuffleQuestionOfQuiz(quiz));
        model.addAttribute("form", new QuizForm(questions));
        return "view-quiz-start";
    }

    @PostMapping("/{quizId}/start")
    public String start(@PathVariable Long quizId,
                        Model model,
                        @ModelAttribute QuizForm quizForm){
        Quiz quiz = this.quizService.getQuiz(quizId);

        model.addAttribute("result", this.quizService.getResult(quiz,quizForm));
        //добавить попытки
        return "view-result";
    }
}
