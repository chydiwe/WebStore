package com.jackass.WebStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @GetMapping("/")
    public String test(HttpServletResponse response) throws IOException {
        return "home.html";
    }

}