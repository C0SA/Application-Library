package com.example.ApplicationLibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home page";
    }

    @GetMapping("/store")
    public String store(){
        return "store page";
    }

    @GetMapping("/admin/home")
    public String getAdminHome(){
        return "admin home page";
    }

    @GetMapping("client/home")
    public String getClientHome(){
        return "client home page";
    }
}
