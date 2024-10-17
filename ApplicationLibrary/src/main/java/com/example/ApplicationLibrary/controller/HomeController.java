package com.example.ApplicationLibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Please Login, POST http://localhost:8080/account/login and in Body in JSON put your username and password and then go to GET http://localhost:8080/books";
    }


}
