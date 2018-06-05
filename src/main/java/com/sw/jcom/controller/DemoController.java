package com.sw.jcom.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/5
 */
@RestController
public class DemoController {

    @RequestMapping("/orders/demo")
    public String getDemo(){

        return "demo";
    }
}
