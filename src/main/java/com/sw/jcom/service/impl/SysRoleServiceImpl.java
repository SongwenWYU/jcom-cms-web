package com.sw.jcom.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sw.jcom.domain.mapper.SysRoleMapper;
import com.sw.jcom.domain.model.SysRole;
import com.sw.jcom.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/9
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(SysRole record) {
        return sysRoleMapper.insertSelective(record);
    }

    @Override
    public SysRole selectByPrimaryKey(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> selectAll() {
        return sysRoleMapper.selectAll();
    }

    @Override
    public PageInfo<SysRole> selectAll(int offset, int limit) {
        PageHelper.offsetPage(offset, limit);
        List<SysRole> sysRoleList = sysRoleMapper.selectAll();
        return new PageInfo<>(sysRoleList);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return sysRoleMapper.updateByPrimaryKey(record);
    }
}
