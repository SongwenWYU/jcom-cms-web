package com.sw.jcom.test.service;

import com.github.pagehelper.PageInfo;
import com.sw.jcom.BaseTestAbstract;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.impl.SysUserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/15
 */
public class SysUserServiceImplTest extends BaseTestAbstract {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @Test
    public void selectTest(){
        PageInfo<SysUser> userPageInfo = sysUserService.select("", "", 1, 1);
        System.out.println(userPageInfo.toString());

        Assert.assertEquals(2, userPageInfo.getTotal());
        Assert.assertEquals(1, userPageInfo.getSize());
    }
}
