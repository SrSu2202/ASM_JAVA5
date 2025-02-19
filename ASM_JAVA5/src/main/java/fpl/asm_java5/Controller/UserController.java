package fpl.asm_java5.Controller;

import fpl.asm_java5.Beans.UserBean;
import fpl.asm_java5.Entities.User;
import fpl.asm_java5.JPA.UserJPA;
import fpl.asm_java5.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserJPA userJPA;

    @GetMapping("/list-users")
    public String users(Model model,
                        @RequestParam("keyword") Optional<String> keyword,
                        @RequestParam("sort") Optional<String> sort) {
        if(keyword.isPresent()) {
            model.addAttribute("users", userJPA.searchAndSortUsers(keyword.get().trim(), sort.get()));
            model.addAttribute("keyword", keyword.get());
            model.addAttribute("sort", sort.get());
            return "/Admin/list-user.html";
        }
        model.addAttribute("sort", "nosort");
        model.addAttribute("users", userJPA.findAll());
        return "/Admin/list-user.html";
    }
    @GetMapping("/addUser")
    public String addUser(Model model, @RequestParam(value = "id") Optional<Integer> id) {
        model.addAttribute("userError", new UserBean());
        if (id.isPresent()) {
            Optional<User> userById = userJPA.findById(id.get());
            UserBean userBean = new UserBean();
            userBean.setId(userById.get().getId());
            userBean.setUsername(userById.get().getUsername());
            userBean.setPassword(userById.get().getPassword());
            userBean.setEmail(userById.get().getEmail());
            userBean.setName(userById.get().getName());
            model.addAttribute("option", "Edit User");
            model.addAttribute("user", userBean);
            return "/Admin/add-user.html";
        }
        model.addAttribute("option", "Add User");
        return "/Admin/add-user.html";
    }
    @GetMapping("/delete-user")
    public String deleteUser(@RequestParam("id") int id){
        boolean delete = userService.deleteUser(id);
        if(!delete){
            return "redirect:/list-users";
        }
        return "redirect:/list-users";
    }
    @PostMapping("/addUser")
    public String handleAddUser(@Valid @ModelAttribute("userError") UserBean userBean, Errors errors, Model model) {
        if(errors.hasErrors() || userBean.isAvatarError() != null){
            model.addAttribute("userError", userBean);
            model.addAttribute("imageError", userBean.isAvatarError());
            model.addAttribute("user", userBean);
            return "/Admin/add-user.html";
        }
        if(userBean.getId() != null){
            String statusUpdate = userService.update(userBean);
            if(statusUpdate != null){
                return "redirect:/list-users";
            } else {
                System.out.println(statusUpdate);
            }
        }
        String statusInsert = userService.insert(userBean);
        if(statusInsert != null){
            return "redirect:/list-users";
        } else {
            System.out.println(statusInsert);
        }
        return "redirect:/list-users";
    }


}
