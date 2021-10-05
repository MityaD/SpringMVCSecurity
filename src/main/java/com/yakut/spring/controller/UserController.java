package com.yakut.spring.controller;

import com.yakut.spring.exception.NoUserTableException;
import com.yakut.spring.model.Address;
import com.yakut.spring.model.Role;
import com.yakut.spring.model.User;
import com.yakut.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> allUser = userService.findAllUser();
        model.addAttribute("allUser", allUser);
        return "users";
    }

    @GetMapping("/users")
    public String usersTablePage() {
        return "users";
    }

    @GetMapping(value = "/add")
    public String getPageSave(Model model, User user) {
        model.addAttribute(user);
        return "userForm";
    }

    @GetMapping("/address")
    public String getAddress(Model model, @RequestParam Long id) throws NoUserTableException {
        User addressById = userService.findUserById(id);
        model.addAttribute("addressById" , addressById);
        return "address";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, @RequestParam Long id) throws NoUserTableException {
        model.addAttribute(userService.findUserById(id));
        return "userForm";
    }

    @PostMapping("/save")
    public String updateUser(@RequestParam (required = false) Long  id, @RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam int age,
                             @RequestParam String password,
                             @RequestParam (name = "address.city") String city,
                             @RequestParam (name = "address.street") String street,
                             @RequestParam (name = "address.house") String house,
                             @RequestParam (defaultValue = "ROLE_USER") String roles) {
        User user = new User(id, firstName, lastName, age, password, new Address(city, street, house),
                Collections.singleton(new Role(1L, roles)));
        userService.save(user);
        return "redirect:/user";
    }
}