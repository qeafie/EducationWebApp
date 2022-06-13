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


    @GetMapping("/edit/{categoryId}")
    public String editCategory(Model model,
                               @PathVariable Long categoryId){
        model.addAttribute("category",this.categoryService.getCategory(categoryId));
        return "edit-category";
    }
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute Category category){
        this.categoryService.updateCategory(category);
        return "redirect:/category/all";
    }


    @PostMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId,Model model){
        Category category = this.categoryService.getCategory(categoryId);
        if (category.getQuizzes().size()==0){
            this.categoryService.deleteCategory(categoryId);
            model.addAttribute("message","Удаление успешно");
        }
        model.addAttribute("message","Удаление не возможно пока есть тесты с этой категорией");
        return "redirect:/category/all";
    }
}
