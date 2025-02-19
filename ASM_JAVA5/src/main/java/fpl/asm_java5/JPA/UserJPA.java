package fpl.asm_java5.JPA;

import fpl.asm_java5.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJPA extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM Users WHERE username = ?1 or email = ?2", nativeQuery = true)
    public List<User> findByUsernameOrEmail(String username, String email);

    @Query(value = "SELECT * FROM USERS WHERE name LIKE CONCAT(N'%',?1,'%') OR username LIKE CONCAT('%',?1,'%') OR email LIKE CONCAT('%',?1,'%') ORDER BY CASE WHEN ?2 = 'asc' THEN username END ASC,  CASE WHEN ?2 = 'desc' THEN username END DESC", nativeQuery = true)
    public List<User> searchAndSortUsers(String keyword, String typeSort);

    @Query(value = "SELECT * FROM Users WHERE (username = ?1 or email = ?2) and id != ?3", nativeQuery = true)
    public List<User> findByUsernameOrEmailAndId(String username, String email, int id);

}
