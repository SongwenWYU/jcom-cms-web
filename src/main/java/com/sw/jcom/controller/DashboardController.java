package com.sw.jcom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * dashboard页面
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/8
 */
@Controller
public class DashboardController {

    @RequestMapping("/dashboard")
    public String getPage(){

        return "/dashboard/dashboard";
    }
}
