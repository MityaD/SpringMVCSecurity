package com.yakut.spring.controller;

import com.yakut.spring.model.Address;
import com.yakut.spring.model.User;
import com.yakut.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public String updateUser(@RequestParam (required = false) Long  id, @RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam int age,
                             @RequestParam (name = "address.city") String city,
                             @RequestParam (name = "address.street") String street,
                             @RequestParam (name = "address.house") String house) {
        User user = new User(id, firstName, lastName, age, new Address(city, street, house));
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }
}