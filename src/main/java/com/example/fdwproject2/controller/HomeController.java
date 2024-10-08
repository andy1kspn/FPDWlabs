package com.example.fdwproject2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/index/desprenoi")
    public String aboutus() {
        return "aboutus";
    }
}
