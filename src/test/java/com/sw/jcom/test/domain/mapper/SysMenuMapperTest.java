package com.sw.jcom.test.domain.mapper;

import com.sw.jcom.BaseTestAbstract;
import com.sw.jcom.domain.mapper.SysMenuMapper;
import com.sw.jcom.domain.model.SysMenu;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/9
 */
public class SysMenuMapperTest extends BaseTestAbstract {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Test
    public void selectByIdsTest(){
        Integer[] ids = {1,2,3};

        List<SysMenu> sysMenuList = sysMenuMapper.selectByIds(ids);

        Assert.assertEquals(sysMenuList.size(), 2);
    }
}
