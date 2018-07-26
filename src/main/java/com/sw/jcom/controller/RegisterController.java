package com.sw.jcom.controller;

import com.sw.jcom.domain.entity.ResultEntity;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/5
 */
@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/register")
    public String register() {
        return "/sys/sysUser-register";
    }

    @ResponseBody
    @RequestMapping("/register/commit")
    public ResultEntity add(SysUser sysUser) {
        if (sysUser == null || StringUtils.isBlank(sysUser.getUsername()) || StringUtils.isBlank(sysUser.getNickname())
                || StringUtils.isBlank(sysUser.getEmail())) {
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        SysUser haveUser = sysUserService.selectByUsername(sysUser.getUsername());
        if (haveUser != null) {
            return new ResultEntity(ResultEntity.Code.USER_REG_EXIST);
        }
        sysUser.setState(SysUser.STATE_NORMAL);
        LocalDateTime now = LocalDateTime.now();
        sysUser.setGmtCreate(now);
        sysUser.setGmtModified(now);
        int addCount = sysUserService.insertSelective(sysUser);
        if (addCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.USER_REG_ERROR);
    }

}
