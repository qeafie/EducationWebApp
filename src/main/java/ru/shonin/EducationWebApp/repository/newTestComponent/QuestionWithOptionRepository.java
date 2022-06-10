package ru.shonin.EducationWebApp.repository.newTestComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.QuestionWithOption;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

import java.util.Set;

public interface QuestionWithOptionRepository extends JpaRepository<QuestionWithOption, Long > {
    Set<QuestionWithOption> findByQuiz(Quiz quiz);

}
