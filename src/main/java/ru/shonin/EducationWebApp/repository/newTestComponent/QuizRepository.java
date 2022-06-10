package ru.shonin.EducationWebApp.repository.newTestComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
