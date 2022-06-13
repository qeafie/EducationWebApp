package ru.shonin.EducationWebApp.controller.newControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.Attempt;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuizForm;
import ru.shonin.EducationWebApp.service.AttemptService;
import ru.shonin.EducationWebApp.service.CategoryService;
import ru.shonin.EducationWebApp.service.QuestionWithOptionService;
import ru.shonin.EducationWebApp.service.QuizService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionWithOptionService questionService;

    @Autowired
    private AttemptService attemptService;



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
        model.addAttribute("quizzes",this.quizService.getActiveQuizzes().stream().filter(x->x.getQuestions().size()>0).collect(Collectors.toSet()));
        model.addAttribute("categories",this.categoryService.getCategories());
        model.addAttribute("currentCategory","All");
        return "view-quizzes";
    }

    @GetMapping("/category/active/{categoryID}")
    public String getActiveAndCategoryQuiz(Model model, @PathVariable Long categoryID){
        Category category = new Category();
        category.setId(categoryID);

        //пользователям выдаём только активные тесты с хотя бы 1 вопросом
        model.addAttribute("quizzes",this.quizService.getActiveQuizzesOfCategory(category).stream().filter(x->x.getQuestions().size()>0).collect(Collectors.toList()));
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
                        @ModelAttribute QuizForm quizForm,
                        @AuthenticationPrincipal User user){
        Quiz quiz = this.quizService.getQuiz(quizId);
        List<QuestionWithOption> list = this.questionService.getQuestionWithFormAnswer(quiz,quizForm);

        double result =this.quizService.getResult(quiz,quizForm);
        double result1 = result1(list,quiz);
        double percent1 =((result*100/quiz.getMaxMarks()));

        Attempt attempt = this.attemptService.addAttempt(new Attempt(quiz,user,result));

        model.addAttribute("percent",this.attemptService.getPercent(quiz,attempt));
        model.addAttribute("percentOfSuccess",(result*100/quiz.getMaxMarks()));
        model.addAttribute("result", result);
        model.addAttribute("attempt",attempt);
        model.addAttribute("maxResult", quiz.getMaxMarks());
        model.addAttribute("questions",list);
        //добавить попытки
        return "view-result";
    }

    double result1(List<QuestionWithOption> questions, Quiz quiz){
        double count=0;
        double step =quiz.getScoresForQuestion();
        for (QuestionWithOption question:
             questions) {
            if (question.getAnswer().equals(question.getFormAnswer()))
                count+=step;
        }
        return count;
    }
}
