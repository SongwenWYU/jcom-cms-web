package com.sw.jcom.service;

import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.domain.model.SysRoleUser;

import java.util.List;

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

    List<SysMenu> selectByRoleUsers(List<SysRoleUser> sysRoleUserList);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}