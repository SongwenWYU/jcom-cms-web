package com.sw.jcom.controller;

import com.sw.jcom.common.Contents;
import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.entity.SysMenuMap;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.domain.model.SysRoleUser;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.SysMenuService;
import com.sw.jcom.service.SysRoleService;
import com.sw.jcom.service.SysRoleUserService;
import com.sw.jcom.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TreeSet;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/5
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${system.name}")
    private String systemName;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/")
    String indexHome(Model model) throws Exception {
        model.addAttribute("title", systemName);
        return "index";
    }

    @RequestMapping("/home")
    String indexPage(Model model, HttpSession session) throws JcomException {
        model.addAttribute("title", systemName);
        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();
        SysUser user = sysUserService.selectByUsername(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("email", user.getEmail());

        //菜单
        List<SysRoleUser> sysRoleUserList = sysRoleUserService.selectByUserId(user.getId());
        if(sysRoleUserList == null || sysRoleUserList.size() == 0){
            return "common/home";
        }
        List<SysMenu> sysMenuList = sysMenuService.selectByRoleUsers(sysRoleUserList);
        TreeSet<SysMenuMap> sysMenuMapList = sysMenuService.menuFormat(sysMenuList);
        model.addAttribute("menu", sysMenuMapList);

        return "common/home";
    }

}