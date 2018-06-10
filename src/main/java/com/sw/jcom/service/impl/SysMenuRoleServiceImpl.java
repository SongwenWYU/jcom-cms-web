package com.sw.jcom.service.impl;

import com.sw.jcom.domain.mapper.SysMenuRoleMapper;
import com.sw.jcom.domain.model.SysMenuRole;
import com.sw.jcom.service.SysMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
@Service
public class SysMenuRoleServiceImpl implements SysMenuRoleService {

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysMenuRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMenuRole record) {
        return sysMenuRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(SysMenuRole record) {
        return sysMenuRoleMapper.insertSelective(record);
    }

    @Override
    public SysMenuRole selectByPrimaryKey(Integer id) {
        return sysMenuRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysMenuRole> selectByRoleId(Integer roleId) {
        return sysMenuRoleMapper.selectByRoleId(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenuRole record) {
        return sysMenuRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysMenuRole record) {
        return sysMenuRoleMapper.updateByPrimaryKey(record);
    }
}
