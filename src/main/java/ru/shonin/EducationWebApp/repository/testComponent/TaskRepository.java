package ru.shonin.EducationWebApp.repository.testComponent;

import org.springframework.data.repository.CrudRepository;
import ru.shonin.EducationWebApp.entity.testComponent.Task;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task,Long> {

    public Iterable<Task> findAllByTestId(Long testId);
    public Optional<Task> findById(Long taskId);
}
