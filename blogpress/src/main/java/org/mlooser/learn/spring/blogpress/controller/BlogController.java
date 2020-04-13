package org.mlooser.learn.spring.blogpress.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class BlogController {

    @GetMapping("/")
    public String homePage(){
        log.info("Showing home page");
        return "home";
    }

}
