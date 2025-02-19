package fpl.asm_java5.beans;

import lombok.Data;
import fpl.asm_java5.Entities.Category;

@Data
public class CategoryBean {
    private int id;
    private String name;

    public CategoryBean() {
    }

    public CategoryBean(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Category toEntity() {
        Category category = new Category();
        category.setId(this.id);
        category.setName(this.name);
        return category;
    }
}
