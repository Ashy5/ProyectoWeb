package com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {


    @GetMapping("/news.html")
    public String news() {
        return "news.html";
    }

}
