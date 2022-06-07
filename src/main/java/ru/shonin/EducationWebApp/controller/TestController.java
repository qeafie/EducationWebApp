package ru.shonin.EducationWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.shonin.EducationWebApp.entity.User;
import ru.shonin.EducationWebApp.entity.testComponent.Test;
import ru.shonin.EducationWebApp.repository.testComponent.TestRepository;

import java.util.Optional;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/tests")
    public String index(Model model){
        Iterable<Test> tests = testRepository.findAll();
        model.addAttribute("tests",tests);
        return "tests";
    }


    @PostMapping("/tests")
    public String filter(@RequestParam String filter, Model model){
        Iterable<Test> tests = testRepository.findAllByTitleContains(filter);
        //model.addAttribute("tests",tests);
        return "tests";

    }

    @GetMapping("/tests/add")
    public String add(){
        return "addTest";
    }

    @PostMapping("/tests/add")
    public String addTest(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String image,
            Model model
    ){
        Test test = new Test(title,description,image,user);
        testRepository.save(test);
        return index(model);
    }


    @GetMapping("/test/{id}")
    public String test(@PathVariable(value = "id") Long id, Model model){
        Optional<Test> test = testRepository.findById(id);

//        if(test.isEmpty())
//            return "Error500.html";

        model.addAttribute("test",test.get());
        return "test";
    }

    @GetMapping("/test/{id}/edit")
    public String testEdit(@PathVariable(value = "id") Long id, Model model){
        return "test-edit";
    }

    @GetMapping("/test/{id}/remove")
    public String testRemove(@PathVariable(value = "id") Long id, Model model){
        return "test-remove";
    }

}
