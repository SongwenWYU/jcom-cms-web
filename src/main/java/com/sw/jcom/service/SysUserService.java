package com.sw.jcom.service;

import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.model.SysUser;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
public interface SysUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    SysUser selectByUsername(String username);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名查询昵称
     * @param username
     * @return
     */
    String getNicknameByUsername(String username) throws JcomException;
}
