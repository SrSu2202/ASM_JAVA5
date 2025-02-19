package fpl.asm_java5.JPA;

import fpl.asm_java5.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductJPA extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Long categoryId);



}
