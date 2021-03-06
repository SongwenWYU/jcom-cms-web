package com.sw.jcom.service;

import com.github.pagehelper.PageInfo;
import com.sw.jcom.domain.model.SysRole;

import java.util.List;

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

    List<SysRole> selectAll();
    PageInfo<SysRole> selectAll(int offset, int limit);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}
