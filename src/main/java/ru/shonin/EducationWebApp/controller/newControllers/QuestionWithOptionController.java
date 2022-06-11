package ru.shonin.EducationWebApp.controller.newControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

        return "redirect:/quiz/all";
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
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
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
}
