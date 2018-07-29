package com.sw.jcom.controller;

import com.sw.jcom.common.Contents;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/29
 */
public abstract class BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前用户
     * @param session
     * @return
     */
    SysUser getUser(HttpSession session){

        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();

        return sysUserService.selectByUsername(username);
    }
}
