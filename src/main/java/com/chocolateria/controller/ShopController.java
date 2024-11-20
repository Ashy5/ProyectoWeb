package com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {


    @GetMapping("/shop.html")
    public String shop() {return "shop.html";
    }

}
