package ru.shonin.EducationWebApp.repository.testComponent;

import org.springframework.data.repository.CrudRepository;
import ru.shonin.EducationWebApp.entity.testComponent.Test;

public interface TestRepository extends CrudRepository<Test,Long> {
}
