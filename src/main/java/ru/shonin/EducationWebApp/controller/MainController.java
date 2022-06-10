package ru.shonin.EducationWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.repository.UserRepository;

@Controller
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model){
        Iterable<User> educationUsers = userRepository.findAll();
        model.addAttribute("educationUsers",educationUsers);
        return "main";
    }
}
