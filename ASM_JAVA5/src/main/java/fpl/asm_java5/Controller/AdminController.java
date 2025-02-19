package fpl.asm_java5.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String admin() {
        return "/Admin/AdminPage.html";
    }
}
