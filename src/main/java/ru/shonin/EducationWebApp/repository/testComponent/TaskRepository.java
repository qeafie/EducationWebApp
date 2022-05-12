package ru.shonin.EducationWebApp.repository.testComponent;

import org.springframework.data.repository.CrudRepository;
import ru.shonin.EducationWebApp.entity.testComponent.Task;

public interface TaskRepository extends CrudRepository<Task,Long> {
}
