package fpl.asm_java5.Service;

import fpl.asm_java5.Beans.ProductBean;
import fpl.asm_java5.Beans.UserBean;
import fpl.asm_java5.Entities.Category;
import fpl.asm_java5.Entities.Images;
import fpl.asm_java5.Entities.Product;
import fpl.asm_java5.Entities.User;
import fpl.asm_java5.JPA.CategoryJPA;
import fpl.asm_java5.JPA.ImagesJPA;
import fpl.asm_java5.JPA.ProductJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    ProductJPA productJPA;

    @Autowired
    CategoryJPA categoryJPA;

    @Autowired
    ImagesJPA imagesJPA;

    @Autowired
    ImageService imageService;

    public String insertProduct(ProductBean productBean) {
        try {
            List<String> fileNames = imageService.saveImagesProduct(productBean.getImages());
            Optional<Category> category = categoryJPA.findById(productBean.getCate());

            Product product = new Product();
            product.setName(productBean.getName());
            product.setDesc(productBean.getDesc());
            product.setPrice(productBean.getPrice());
            product.setQuantity(productBean.getQuantity());
            product.setCategory(category.get());
            productJPA.save(product);
            List<Images> images = new ArrayList<Images>();
            for(String fileName : fileNames) {
                Images image = new Images();
                image.setImage(fileName);
                image.setProduct(product);
                images.add(image);
            }
            imagesJPA.saveAll(images);
        } catch (Exception e) {
            return "co loi khi them san pham";
        }
        return null;
    }
    public Boolean deleteProduct(int id) {
        try {
            Optional<Product> product = productJPA.findById(id);
            if(!product.isPresent()){
                return false;
            }
            productJPA.delete(product.get());

        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public void updateProduct(ProductBean productBean) {
        // Kiểm tra sự tồn tại của sản phẩm
        Product existingProduct = productJPA.findById(productBean.getId()).orElse(null);

        if (existingProduct == null) {
            // Nếu không tìm thấy sản phẩm, trả về hoặc xử lý lỗi
            return;
        }

        // Cập nhật thông tin sản phẩm
        existingProduct.setName(productBean.getName());
        existingProduct.setDesc(productBean.getDesc());
        existingProduct.setPrice(productBean.getPrice());
        existingProduct.setQuantity(productBean.getQuantity());

        // Cập nhật danh mục
        Category category = categoryJPA.findById(productBean.getCate()).orElse(null);
        if (category != null) {
            existingProduct.setCategory(category);
        }

        // Xử lý ảnh nếu có
        if (productBean.getImages() != null && !productBean.getImages().isEmpty()) {
            List<String> imageNames = imageService.saveImagesProduct(productBean.getImages());
            List<Images> images = new ArrayList<>();
            for (String imageName : imageNames) {
                Images image = new Images();
                image.setImage(imageName);
                image.setProduct(existingProduct);
                images.add(image);
            }
            existingProduct.setImages(images);
        }

        // Lưu thông tin sản phẩm đã cập nhật
        productJPA.save(existingProduct);
    }
    public List<Product> getAllProducts() {
        return productJPA.findAll();  // Giả sử bạn đang sử dụng JPA
    }

    public List<Product> findProductsByCategory(Long categoryId) {
        return productJPA.findByCategoryId(categoryId);
    }



}
