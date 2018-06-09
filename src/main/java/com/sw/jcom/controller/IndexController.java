package com.sw.jcom.controller;

import com.sw.jcom.common.Contents;
import com.sw.jcom.common.exception.ExceptionEnum;
import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.mapper.SysRoleMapper;
import com.sw.jcom.domain.mapper.SysRoleUserMapper;
import com.sw.jcom.domain.mapper.SysUserMapper;
import com.sw.jcom.domain.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${system.name}")
    private String systemName;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleUserMapper roleUserMapper;

    @RequestMapping("/")
    String indexHome(Model model) throws Exception {
        model.addAttribute("title", systemName);
        return "index";
    }

    @RequestMapping("/index")
    String indexPage(Model model, HttpSession session) throws Exception {
        model.addAttribute("title", systemName);
        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();
        SysUser sysUser = userMapper.selectByUsername(username);
        model.addAttribute("username", sysUser.getNickname());

        //菜单

        return "common/index";
    }

}