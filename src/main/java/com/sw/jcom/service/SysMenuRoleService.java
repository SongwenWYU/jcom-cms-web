package com.sw.jcom.service;

import com.sw.jcom.domain.model.SysMenuRole;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
public interface SysMenuRoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    SysMenuRole selectByPrimaryKey(Integer id);

    List<SysMenuRole> selectByRoleId(Integer roleId);

    int updateByPrimaryKeySelective(SysMenuRole record);

    int updateByPrimaryKey(SysMenuRole record);
}
