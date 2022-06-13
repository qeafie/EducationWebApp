package ru.shonin.EducationWebApp.service;

import ru.shonin.EducationWebApp.entity.Attempt;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

import java.util.List;

public interface AttemptService {

    public Attempt getAttempt(Long attemptId);

    public List<Attempt> getAttempts();

    public  List<Attempt> getAttemptsOfUser(User user);

    public Attempt addAttempt(Attempt attempt);

    public int getPercent (Quiz quiz,Attempt attempt);

    public int getPosition(Attempt attempt,Quiz quiz);
}
