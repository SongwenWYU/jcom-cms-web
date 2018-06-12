package com.sw.jcom.test.service;

import com.sw.jcom.BaseTestAbstract;
import com.sw.jcom.domain.entity.SysMenuMap;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.service.impl.SysMenuServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/11
 */
public class SysMenuServiceImplTest extends BaseTestAbstract {

    @Autowired
    private SysMenuServiceImpl sysMenuService;


    @Test
    public void menuFormatTest() {
        List<SysMenu> sysMenuList = sysMenuService.selectAll();
        TreeSet<SysMenuMap> sysMenuMapTreeSet = sysMenuService.menuFormat(sysMenuList);
        Iterator<SysMenuMap> iterator = sysMenuMapTreeSet.iterator();
        while (iterator.hasNext()) {
            SysMenuMap sysMenuMap = iterator.next();
            String ss = "-";
            System.out.printf(ss + sysMenuMap.getSysMenu().toString());
            print(sysMenuMap, ss);
        }

    }

    public void print(SysMenuMap sysMenuMap, String ss){
        System.out.println(ss + sysMenuMap.getSysMenu().toString());
        if (sysMenuMap.isParent()){
            ss += "-";
            Iterator<SysMenuMap> iterator = sysMenuMap.getSysMenuMaps().iterator();
            while (iterator.hasNext()) {
                print(iterator.next(), ss);
            }
        }
    }
}
