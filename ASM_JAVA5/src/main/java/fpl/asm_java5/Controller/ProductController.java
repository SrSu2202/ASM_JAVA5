package fpl.asm_java5.Controller;

import fpl.asm_java5.Beans.ProductBean;
import fpl.asm_java5.Entities.Category;
import fpl.asm_java5.Entities.Product;
import fpl.asm_java5.JPA.CategoryJPA;
import fpl.asm_java5.JPA.ProductJPA;
import fpl.asm_java5.Service.CategoryService;
import fpl.asm_java5.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ProductController {
    @Autowired
    ProductJPA productJPA;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryJPA categoryJPA;

    @Autowired
    CategoryService categoryService;
    @GetMapping("/add-product")
    public String addProduct(Model model) {
        List<Category> categories = categoryJPA.findAll();
        model.addAttribute("productBean", new ProductBean());
        model.addAttribute("cate", categories);
        return "/Admin/add-product.html";
    }

    @GetMapping("/detail-Product")
    public String detailProduct(Model model) {
        List<Product> products = productJPA.findAll();
        model.addAttribute("products", products);
        return "/Admin/detail-product.html";
    }
    @PostMapping("/addProduct2")
    public String addProduct2(@Valid @ModelAttribute("productBean") ProductBean productBean, Errors errors, Model model) {
        if (errors.hasErrors() || productBean.errorImage() != null) {
            List<Category> categories = categoryJPA.findAll();
            model.addAttribute("cate", categories);
            model.addAttribute("errorImage", productBean.errorImage());
            return "/Admin/add-product.html";
        }
        productService.insertProduct(productBean);
        return "redirect:/detail-Product";
    }
    @GetMapping("/delete-product")
    public String deleteUser(@RequestParam("id") int id){
        boolean delete = productService.deleteProduct(id);
        if(!delete){
            return "redirect:/detail-Product";
        }
        return "redirect:/detail-Product";
    }

    @GetMapping("/edit-product")
    public String editProduct(@RequestParam("id") int id, Model model) {
        // Lấy thông tin sản phẩm từ database
        Product product = productJPA.findById(id).orElse(null);

        if (product == null) {
            return "redirect:/detail-Product"; // Nếu không tìm thấy sản phẩm, quay lại trang chi tiết
        }

        // Chuyển thông tin sản phẩm sang ProductBean để dễ dàng hiển thị trong form
        ProductBean productBean = new ProductBean();
        productBean.setId(product.getId());
        productBean.setName(product.getName());
        productBean.setDesc(product.getDesc());
        productBean.setPrice(product.getPrice());
        productBean.setQuantity(product.getQuantity());
        productBean.setCate(product.getCategory().getId());

        List<Category> categories = categoryJPA.findAll();
        model.addAttribute("productBean", productBean);
        model.addAttribute("cate", categories);
        return "/Admin/edit-product.html"; // Chuyển tới form sửa sản phẩm
    }

    // Xử lý form sửa sản phẩm
    @PostMapping("/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("productBean") ProductBean productBean, Errors errors, Model model) {
        if (errors.hasErrors() || productBean.errorImage() != null) {
            List<Category> categories = categoryJPA.findAll();
            model.addAttribute("cate", categories);
            model.addAttribute("errorImage", productBean.errorImage());
            return "/Admin/edit-product.html"; // Nếu có lỗi, quay lại trang sửa
        }

        productService.updateProduct(productBean); // Cập nhật sản phẩm
        return "redirect:/detail-Product"; // Quay lại trang chi tiết sản phẩm
    }
    @GetMapping("/home")
    public String homePage(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productJPA.findAll());
        return "/view/home";
    }

    @GetMapping("/products/category/{id}")
    public String showProductsByCategory(@PathVariable("id") Long categoryId, Model model) {
        // Lấy danh sách sản phẩm theo danh mục
        List<Product> products = productService.findProductsByCategory(categoryId);
        model.addAttribute("products", products);
        return "/view/productByCate.html";
    }







}
