package com.sw.jcom.service.impl;

import com.sw.jcom.domain.mapper.SysRoleUserMapper;
import com.sw.jcom.domain.model.SysRoleUser;
import com.sw.jcom.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysRoleUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysRoleUser record) {
        return sysRoleUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysRoleUser record) {
        return sysRoleUserMapper.insertSelective(record);
    }

    @Override
    public SysRoleUser selectByPrimaryKey(Integer id) {
        return sysRoleUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRoleUser> selectByUserId(Integer userId) {
        return sysRoleUserMapper.selectByUserId(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRoleUser record) {
        return sysRoleUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRoleUser record) {
        return sysRoleUserMapper.updateByPrimaryKey(record);
    }
}
