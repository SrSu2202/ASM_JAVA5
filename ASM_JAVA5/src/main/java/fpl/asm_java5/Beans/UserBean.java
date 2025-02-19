package fpl.asm_java5.Beans;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {
    private Integer id;
    @NotBlank(message = "Không được để trống Username")
    private String username;

    @NotBlank(message = "Không được để trống Password")
    @Length(min = 6, message = "Password hơn 6 ký tự")
    private String password;

    @NotBlank(message = "Không được để trống Email")
    @Email(message = "Email phải đúng định dạng")
    private String email;

    @NotBlank(message = "Không được để trống Name")
    private String name;

    @NotNull(message = "Không được để trống Avatar")
    private MultipartFile avatar;

    private int role;

    public String isAvatarError() {
        if (avatar.isEmpty()) {
            return "Avatar bắt buộc phai them";
        }
        if (avatar.getSize() > (20 * 1024 * 1024)) {
            return "Dung lương ảnh dưới 20MB";
        }
        return null;
    }
}
