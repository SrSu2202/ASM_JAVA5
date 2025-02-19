package fpl.asm_java5.Service;

import fpl.asm_java5.Entities.Category;
import fpl.asm_java5.JPA.CategoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryJPA categoryJPA;

    public List<Category> getAllCategories() {
        return categoryJPA.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryJPA.findById(id);
    }

    public Category saveCategory(Category category) {
        return categoryJPA.save(category);
    }

    public void deleteCategory(int id) {
        categoryJPA.deleteById(id);
    }

}
