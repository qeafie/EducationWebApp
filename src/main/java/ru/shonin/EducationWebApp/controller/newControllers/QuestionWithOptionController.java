package ru.shonin.EducationWebApp.controller.newControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
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

    @GetMapping("/{quizId}/add")
    public String addQuestion(Model model,@PathVariable Long quizId){
        QuestionWithOption question = new QuestionWithOption();
        model.addAttribute("question",question);
        model.addAttribute("quizId",quizId);
        return "add-question";
    }
    @PostMapping("/{quizId}/add")
    public String addQuestion(@ModelAttribute QuestionWithOption question,
                              @RequestParam String answerNumber,
                              @PathVariable Long quizId){
        question.setAnswer(question.getAnswerByNumber(Long.parseLong(answerNumber)));
        Quiz quiz = this.quizService.getQuiz(quizId);
        question.setQuiz(quiz);
        quiz.addQuestion(this.questionService.addQuestion(question));

        return "redirect:/question/quiz/all/"+quizId;
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
        if (list.size()>quiz.getNumberOfQuestions()){
            list = list.subList(0,quiz.getNumberOfQuestions()+1);
        }
        Collections.shuffle(list);
        model.addAttribute("Questions",list);
        return "questionOfQuiz";
    }

    @GetMapping("/quiz/all/{quizId}")
    public String getQuestionOfQuizAdmin(@PathVariable("quizId") Long quizID,
                                         Model model){
        Quiz quiz = new Quiz();
        quiz.setId(quizID);
        Set<QuestionWithOption> questions = this.questionService.getQuestionOfQuiz(quiz);
        model.addAttribute("questions", questions);
        return "view-questions";
    }

    @GetMapping("/edit/{questionId}")
    public String editQuestion(Model model,
                               @PathVariable Long questionId){
        model.addAttribute("question",this.questionService.getQuestion(questionId));
        return "edit-question";
    }
    @PostMapping("/edit/{quizID}")
    public String editQuestion(@ModelAttribute QuestionWithOption question,
                               @RequestParam String answerNumber,
                               @PathVariable Long quizID){
        question.setAnswer(question.getAnswerByNumber(Long.parseLong(answerNumber)));
        QuestionWithOption question1 = this.questionService.updateQuestion(question);


        return "redirect:/question/quiz/all/"+this.quizService.getQuiz(quizID).getId();
    }


    @PostMapping("/delete/{questionId}")
    public String deleteQuestion(@PathVariable Long questionId,
                                 Model model){
        this.questionService.deleteQuestion(questionId);
        model.addAttribute("message","Удаление успешно");

        return "redirect:/";
    }
}
