package ru.shonin.EducationWebApp.controller.newControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shonin.EducationWebApp.entity.newTestComponent.Category;
import ru.shonin.EducationWebApp.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public String allCategory(Model model){

        model.addAttribute("categories", this.categoryService.getCategories());
        return "categories";
    }
    @GetMapping("/add")
    public String addCategory(Model model){

        Category category = new Category();
        model.addAttribute("category",category);
        return "add-category";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category){
        this.categoryService.addCategory(category);
        return "redirect:/category/all";
    }

    @GetMapping("/{categoryId}")
    public String getCategory(@PathVariable("categoryId") Long categoryId,
                              Model model){
        if (this.categoryService.getCategory(categoryId)!=null){
            model.addAttribute("category",this.categoryService.getCategory(categoryId));
            return "category";
        }
        return "error500";
    }

}
