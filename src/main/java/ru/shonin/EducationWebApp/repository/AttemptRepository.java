package ru.shonin.EducationWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.shonin.EducationWebApp.entity.Attempt;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt,Long> {
    List<Attempt> findByUser(User user);
    List<Attempt> findByQuiz(Quiz quiz);
    //List<Attempt> findByQuizAndOrderByResult(Quiz quiz,Attempt attempt);
    int countAllByQuiz(Quiz quiz);
    List<Attempt> findByQuizOrderByResultDesc(Quiz quiz);
}
