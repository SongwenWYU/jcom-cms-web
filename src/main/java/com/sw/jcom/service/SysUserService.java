package com.sw.jcom.service;

import com.github.pagehelper.PageInfo;
import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.model.SysUser;

import java.util.List;

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

    PageInfo<SysUser> select(String username, String nickName, int currentPage, int pageSize);

    PageInfo<SysUser> selectAdmin(String username, String nickname, int currentPage, int pageSize);

    /**
     * 管理员查询使用
     * @param id
     * @return
     */
    SysUser selectAdminById(Integer id);

    /**
     * 普通用户查询个人信息使用
     * @param id
     * @return
     */
    SysUser selectUserById(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名查询昵称
     * @param username
     * @return
     */
    String getNicknameByUsername(String username) throws JcomException;
}
