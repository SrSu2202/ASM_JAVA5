package fpl.asm_java5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String showLoginPage() {
        return "/view/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/view/register";
    }
//    @GetMapping("/home")
//    public String home() {
//        return "/view/home";
//    }
    @GetMapping("/info")
    public String info() {
        return "/view/info";
    }
    @GetMapping("/detail")
    public String detail() {
        return "/view/detail";
    }
    @GetMapping("/changepass")
    public String changepass() {
        return "/view/changepass";
    }
    @GetMapping("/cart")
    public String cart() {
        return "/view/cart";
    }
    @GetMapping("/payment")
    public String pay() {
        return "/view/payment";
    }

}