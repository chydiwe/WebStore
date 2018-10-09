package com.jackass.RestAPI.controller;

import com.jackass.RestAPI.mail.MailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class TestController {

    @Autowired
    MailManager mailManager;

    @GetMapping("/")
    public void test(HttpServletResponse response) throws IOException {
        mailManager.sendToken("ruslan999_97@mail.ru", "123232");
    }

}