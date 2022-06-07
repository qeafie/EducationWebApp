package ru.shonin.EducationWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.repository.UserRepository;

@Controller()
public class UserController {

    @Autowired
    UserRepository userRepository;
    @GetMapping("user/profile")
    public String profile(@AuthenticationPrincipal User user,
                          Model model){
        model.addAttribute("user",user);
        return "profile";
    }

    @GetMapping("user/{id}")
    public String profileId(@PathVariable(value = "id") Long id,
                            Model model){
        User user = userRepository.findById(id).get();
        model.addAttribute("user",user);
        return "profile";
    }
}
