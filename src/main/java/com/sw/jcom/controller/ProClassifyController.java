package com.sw.jcom.controller;

import com.sw.jcom.domain.model.ProClassify;
import com.sw.jcom.service.ProClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/26
 */
@Controller
public class ProClassifyController extends BaseController{

    @Autowired
    private ProClassifyService proClassifyService;

    private String page = "/pro/pro-classify.html";

    @RequestMapping("/u/pro/classify/page")
    public String getPage() {
        return page;
    }

    /**
     * 查询用户下的分类
     * @param status 状态
     * @param httpSession
     * @return
     */
    public List<ProClassify> select(Integer[] status, HttpSession httpSession){

        return null;
    }

    /**
     * 查询某个分类
     * @param id
     * @return
     */
    public ProClassify select(Integer id, HttpSession httpSession){

        return null;
    }


}
