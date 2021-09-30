package com.yakut.spring.controller;

import com.yakut.spring.exception.NoUserTableException;
import com.yakut.spring.model.User;
import com.yakut.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@AllArgsConstructor
public class JustAPageController {

    private final UserService userService;

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);
        return "user";
    }

    @GetMapping("/address")
    public String getAddress(Model model, @RequestParam Long id) throws NoUserTableException {
        User addressById = userService.findUserById(id);
        model.addAttribute("addressById" , addressById);
        return "address";
    }

    @GetMapping(value = "/add")
    public String getPageSave(Model model, User user) {
        model.addAttribute(user);
        return "userForm";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, @RequestParam Long id) throws NoUserTableException {
        model.addAttribute(userService.findUserById(id));
        return "userForm";
    }
}
