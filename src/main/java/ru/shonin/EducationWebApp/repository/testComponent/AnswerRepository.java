package ru.shonin.EducationWebApp.repository.testComponent;

import org.springframework.data.repository.CrudRepository;
import ru.shonin.EducationWebApp.entity.testComponent.Answer;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
}
