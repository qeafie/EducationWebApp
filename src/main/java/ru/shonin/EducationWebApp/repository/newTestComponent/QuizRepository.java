package ru.shonin.EducationWebApp.repository.newTestComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.entity.newTestComponent.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    public Set<Quiz> findByCategory(Category category);
    public List<Quiz> findByActive(Boolean b);
    public List<Quiz> findByCategoryAndActive(Category category,Boolean b);
}
