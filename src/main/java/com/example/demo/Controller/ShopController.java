package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shop")
public class ShopController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index()
    {
        return "shop";
    }
}
