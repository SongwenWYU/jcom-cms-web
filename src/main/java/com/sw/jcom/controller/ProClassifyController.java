package com.sw.jcom.controller;

import com.sw.jcom.service.ProClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/26
 */
@Controller
public class ProClassifyController {

    @Autowired
    private ProClassifyService proClassifyService;

    private String page = "/pro/pro-classify.html";

    @RequestMapping("/u/pro/classify/page")
    public String register() {
        return page;
    }


}
