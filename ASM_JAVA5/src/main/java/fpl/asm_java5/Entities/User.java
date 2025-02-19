package fpl.asm_java5.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "name", length = 255, nullable = false, columnDefinition = "nvarchar") //hoặc anotation @Nationalized
    private String name;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "role", nullable = false)
    private int role;

    @Column(name = "avatar", length = 255, nullable = false)
    private String avatar;

    public User(String username, String password, String name, String email, boolean isActive, int role, String avatar) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isActive = isActive;
        this.role = role;
        this.avatar = avatar;
    }
}
