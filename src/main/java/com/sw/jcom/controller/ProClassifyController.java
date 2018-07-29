package com.sw.jcom.controller;

import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.model.ProClassify;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.ProClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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
     * @param id 父节点ID
     * @param status 状态
     * @param httpSession
     * @return
     */
    @PostMapping("/u/pro/classify/select")
    public List<ProClassify> select(Integer id, Integer[] status, HttpSession httpSession) throws JcomException {
        SysUser user = getUser(httpSession);
        return proClassifyService.selectChildrens(id, status, user.getId());
    }

    /**
     * 查询某个分类
     * @param id
     * @return
     */
    @PostMapping("/u/pro/classify/selectById")
    public ProClassify select(Integer id, HttpSession httpSession){

        return null;
    }


}
