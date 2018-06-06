package com.sw.jcom.service;

import com.sw.jcom.domain.model.SysRole;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/6
 */
public interface SysRoleService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
