package com.yakut.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)//todo кароч понял
    public String loginPage() {
        return "login";
    }
}
