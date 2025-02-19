package fpl.asm_java5.JPA;

import fpl.asm_java5.Entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesJPA extends JpaRepository<Images, Integer> {
}
