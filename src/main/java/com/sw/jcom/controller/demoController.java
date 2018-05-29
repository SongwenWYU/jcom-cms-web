package com.sw.jcom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class demoController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello")
    @ResponseBody
    String index() {
        logger.info("hello world.");
        return "hello world.";
    }

    @RequestMapping("/thymeleaf")
    String thymeleaf(Model model) {
        model.addAttribute("name", "Dear");
        return "index";
    }

}