package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // Read
    @GetMapping(value = {"/", "/users"})
    public String getAllUsers(ModelMap model) {
        List<User> listUsers = userService.getAll();
        model.addAttribute("users", listUsers);
        model.addAttribute("newUser", new User());
        return "users";
    }

    // Create
    @GetMapping(value = "/createUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    // Delete
    @GetMapping("/deleteUser")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }

    // Edit
    @GetMapping(value = "/editUser")
    public String editUser(ModelMap model, @RequestParam("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/update_user";
    }

    @GetMapping(value = "/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/";
    }
}
