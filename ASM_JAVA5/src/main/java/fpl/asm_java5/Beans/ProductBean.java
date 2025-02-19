package fpl.asm_java5.Beans;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBean {
    private Integer id;
    @NotBlank(message = "Tên không được bỏ trống")
    private String name;

    @Length(min = 20, max = 200, message = "Mô tả sản phẩm phải từ 20 đến 200 ký tự")
    private String desc;

    @Min(value = 10000, message = "Giá tiền phải lớn hơn hoặc bằng 10000")
    private int price;

    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1")
    private int quantity;

    @Min(value = 0, message = "Danh mục Bắt buộc chọn")
    private int cate;

    private List<MultipartFile> images;

    public String errorImage() {
        if(images.size() < 3) {
            return "Ảnh phải từ 3 hình trở lên";
        }
        int size = 0;
        for(MultipartFile image : images) {
            size += image.getSize();
        }
        if( (size / (1024*1024) ) > 20) {
            return "Độ lớn của các ảnh phải bé hơn 20MB";
        }
        return null;
    }
}
