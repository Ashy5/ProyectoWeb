package com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {


    @GetMapping("/about.html")
    public String about() {
        return "about.html";
    }

}
