package com.sw.jcom.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sw.jcom.common.exception.ExceptionEnum;
import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.mapper.SysUserMapper;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public SysUser selectByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public PageInfo<SysUser> select(String username, String nickName, int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<SysUser> sysUserList = sysUserMapper.select(username, nickName);
        return new PageInfo<>(sysUserList);
    }

    @Override
    public PageInfo<SysUser> selectAdmin(String username, String nickname, int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<SysUser> sysUserList = sysUserMapper.selectAdmin(username, nickname);
        return new PageInfo<>(sysUserList);
    }

    @Override
    public SysUser selectAdminById(Integer id) {
        return sysUserMapper.selectAdminById(id);
    }

    @Override
    public SysUser selectUserById(Integer id) {
        return sysUserMapper.selectUserById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public String getNicknameByUsername(String username) throws JcomException {
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        if(sysUser == null){
            throw new JcomException(ExceptionEnum.NO_USER);
        }
        return sysUser.getNickname();
    }
}
