package com.sw.jcom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("system.name")
    private String systemName;

    @RequestMapping("/")
    String index(Model model) throws Exception {
        model.addAttribute("title", "Mango - JCOM");
        return "index";
    }

}