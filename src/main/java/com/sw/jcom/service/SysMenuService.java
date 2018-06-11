package com.sw.jcom.service;

import com.sw.jcom.domain.entity.SysMenuMap;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.domain.model.SysRoleUser;

import java.util.List;
import java.util.TreeSet;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
public interface SysMenuService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    List<SysMenu> selectByIds(Integer[] ids);

    List<SysMenu> selectByRoleIds(Integer[] roleIds);

    List<SysMenu> selectAll();

    List<SysMenu> selectByRoleUsers(List<SysRoleUser> sysRoleUserList);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    /**
     * 将菜单列表格式化为有序的菜单列表
     * @param sysMenuList
     * @return
     */
    TreeSet<SysMenuMap> menuFormat(List<SysMenu> sysMenuList);
}
