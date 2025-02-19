package fpl.asm_java5.Controller;

import fpl.asm_java5.Entities.Category;
import fpl.asm_java5.Service.CategoryService;
import fpl.asm_java5.beans.CategoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list-categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/Admin/list-category.html";
    }

    @GetMapping("/add-category")
    public String addCategoryForm(Model model) {
        model.addAttribute("categoryBean", new CategoryBean());
        return "/Admin/add-category.html";
    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute CategoryBean categoryBean) {
        categoryService.saveCategory(categoryBean.toEntity());
        return "redirect:/list-categories";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategoryForm(@PathVariable int id, Model model) {
        Category category = categoryService.getCategoryById(id).orElseThrow();
        model.addAttribute("categoryBean", new CategoryBean(category));
        return "/Admin/edit-category.html";
    }

    @PostMapping("/edit-category")
    public String editCategory(@ModelAttribute CategoryBean categoryBean) {
        categoryService.saveCategory(categoryBean.toEntity());
        return "redirect:/list-categories";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/list-categories";
    }
}
