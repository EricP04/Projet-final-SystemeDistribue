package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}
