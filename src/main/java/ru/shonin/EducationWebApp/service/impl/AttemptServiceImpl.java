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
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
        int percent = 0;//100
        int countOfAttempt = this.attemptRepository.countAllByQuiz(quiz);
        int countOfAttempt2 = this.attemptRepository.findByQuiz(quiz).stream().collect(Collectors.groupingBy(Attempt::getResult)).size();
        int indexOfAttempt = getPosition(attempt,quiz);
        return ((indexOfAttempt*100)/countOfAttempt2);
    }

    @Override
    public int getPosition(Attempt attempt,Quiz quiz) {

        Map<Double,List<Attempt>> attemptSet = this.attemptRepository.findByQuizOrderByResultDesc(quiz).stream()
                .collect(Collectors.groupingBy(Attempt::getResult));

        TreeSet<Double> set = new TreeSet<>( attemptSet.keySet());

        System.out.println( set.headSet(attempt.getResult()).size());
        System.out.println(set);
        return set.headSet(attempt.getResult()).size();
        //return this.attemptRepository.findByQuizOrderByResultDesc(quiz).indexOf(attempt);
        //return this.attemptRepository.findAll(Sort.by(Sort.Direction.DESC,"result")).indexOf(attempt);
    }
}
