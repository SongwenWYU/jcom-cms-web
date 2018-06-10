package com.sw.jcom.service.impl;

import com.sw.jcom.domain.mapper.SysMenuMapper;
import com.sw.jcom.domain.mapper.SysMenuRoleMapper;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.domain.model.SysMenuRole;
import com.sw.jcom.domain.model.SysRoleUser;
import com.sw.jcom.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysMenu record) {
        return sysMenuMapper.insert(record);
    }

    @Override
    public int insertSelective(SysMenu record) {
        return sysMenuMapper.insertSelective(record);
    }

    @Override
    public SysMenu selectByPrimaryKey(Integer id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysMenu> selectByIds(Integer[] ids) {
        return sysMenuMapper.selectByIds(ids);
    }

    @Override
    public List<SysMenu> selectByRoleIds(Integer[] roleIds) {
        List<SysMenuRole> sysMenuRoleList = sysMenuRoleMapper.selectByRoleIds(roleIds);
        if(sysMenuRoleList == null || sysMenuRoleList.size() == 0){
            return new ArrayList<SysMenu>();
        }

        Integer[] menuIds = new Integer[sysMenuRoleList.size()];
        for (int i = 0; i < sysMenuRoleList.size() ; i++) {
            menuIds[i] = sysMenuRoleList.get(i).getMenuId();
        }

        return sysMenuMapper.selectByIds(menuIds);
    }

    @Override
    public List<SysMenu> selectByRoleUsers(List<SysRoleUser> sysRoleUserList) {
        if(sysRoleUserList == null || sysRoleUserList.size() == 0){
            return new ArrayList<>();
        }
        Integer[] roleIds = new Integer[sysRoleUserList.size()];
        for (int i = 0; i < sysRoleUserList.size(); i++) {
            roleIds[i] = sysRoleUserList.get(i).getRoleId();
        }
        return selectByRoleIds(roleIds);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysMenu record) {
        return sysMenuMapper.updateByPrimaryKey(record);
    }
}
