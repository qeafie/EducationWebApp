package ru.shonin.EducationWebApp.repository.testComponent;

import org.springframework.data.repository.CrudRepository;
import ru.shonin.EducationWebApp.entity.testComponent.Question;

public interface QuestionRepository extends CrudRepository<Question,Long> {
}
