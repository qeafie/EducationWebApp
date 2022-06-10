package ru.shonin.EducationWebApp.service;

import org.springframework.stereotype.Service;
import ru.shonin.EducationWebApp.entity.testComponent.Question;
import ru.shonin.EducationWebApp.repository.testComponent.QuestionRepository;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public Question addQuestion(String text){
        Question localQuestion = new Question(text);

        return localQuestion;
    }


}
