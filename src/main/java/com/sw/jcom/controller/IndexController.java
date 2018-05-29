package com.sw.jcom.controller;

import com.sw.jcom.domain.entity.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/index")
    Demo index() throws Exception {
        Demo demo = new Demo();
        demo.setName("中文");
        demo.setAge("21");
        throw new Exception("123");
//        return demo;
    }

}