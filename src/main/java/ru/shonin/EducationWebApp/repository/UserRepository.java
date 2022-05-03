package ru.shonin.EducationWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shonin.EducationWebApp.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
