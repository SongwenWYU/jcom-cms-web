package com.sw.jcom.controller;

import com.sw.jcom.domain.entity.DataTablesInfo;
import com.sw.jcom.domain.model.SysRole;
import com.sw.jcom.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/13
 */
@Controller
public class SysRoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String thisPage = "/sys/sysRole";

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/au/sysRole")
    public String getPage() {
        return SysRoleController.thisPage;
    }

    @RequestMapping("/au/role/getAll")
    @ResponseBody
    public DataTablesInfo<SysRole> selectAll(int start, int length, HttpServletRequest request) {
        return new DataTablesInfo<SysRole>(sysRoleService.selectAll(start, length), request);
    }
}
