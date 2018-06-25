package com.sw.jcom.controller;

import com.sw.jcom.domain.entity.SysMenuMap;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.TreeSet;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/25
 */
@Controller
public class SysMenuController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String thisPage = "/sys/sysMenu";

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/au/menu")
    public String getPage(){
        return SysMenuController.thisPage;
    }

    @PostMapping("/au/menu/getAll")
    @ResponseBody
    public TreeSet<SysMenuMap> getAllMenu(){
        List<SysMenu> sysMenuList = sysMenuService.selectAll();
        return sysMenuService.menuFormat(sysMenuList);
    }
}
