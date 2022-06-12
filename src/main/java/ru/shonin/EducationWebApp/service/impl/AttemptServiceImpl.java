package ru.shonin.EducationWebApp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.shonin.EducationWebApp.entity.Attempt;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;
import ru.shonin.EducationWebApp.repository.AttemptRepository;
import ru.shonin.EducationWebApp.service.AttemptService;

import java.util.List;
@Service
public class AttemptServiceImpl implements AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Override
    public Attempt getAttempt(Long attemptId) {
        return this.attemptRepository.getById(attemptId);
    }

    @Override
    public List<Attempt> getAttempts() {
        return this.attemptRepository.findAll();
    }

    @Override
    public List<Attempt> getAttemptsOfUser(User user) {
        return this.attemptRepository.findByUser(user);
    }

    @Override
    public Attempt addAttempt(Attempt attempt) {
        return this.attemptRepository.save(attempt);
    }

    @Override
    public int getPercent(Quiz quiz, Attempt attempt) {
        int percent = 0;
        int countOfAttempt = this.attemptRepository.countAllByQuiz(quiz);
        int indexOfAttempt = getPosition(attempt);
        return (indexOfAttempt*100)/countOfAttempt;
    }

    @Override
    public int getPosition(Attempt attempt) {
        return this.attemptRepository.findAll(Sort.by(Sort.Direction.ASC,"result")).indexOf(attempt);
    }
}
