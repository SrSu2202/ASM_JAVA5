package fpl.asm_java5.JPA;

import fpl.asm_java5.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJPA extends JpaRepository<Category, Integer> {
}
