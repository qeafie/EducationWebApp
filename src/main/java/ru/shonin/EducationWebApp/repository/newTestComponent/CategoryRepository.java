package ru.shonin.EducationWebApp.repository.newTestComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
