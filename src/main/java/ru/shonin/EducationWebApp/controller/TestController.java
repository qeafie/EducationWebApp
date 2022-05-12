package ru.shonin.EducationWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.testComponent.Test;
import ru.shonin.EducationWebApp.repository.testComponent.TestRepository;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public String index(Model model){
        Iterable<Test> tests = testRepository.findAll();
        model.addAttribute("tests",tests);
        return "tests";
    }
}
