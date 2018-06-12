package com.sw.jcom.service.impl;

import com.sw.jcom.domain.entity.SysMenuMap;
import com.sw.jcom.domain.mapper.SysMenuMapper;
import com.sw.jcom.domain.mapper.SysMenuRoleMapper;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.domain.model.SysMenuRole;
import com.sw.jcom.domain.model.SysRoleUser;
import com.sw.jcom.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<SysMenu> selectAll() {
        return sysMenuMapper.selectAll();
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

    @Override
    public TreeSet<SysMenuMap> menuFormat(List<SysMenu> sysMenuList) {
        TreeSet<SysMenuMap> sysMenuMaps = new TreeSet<>(SysMenuMap.MENU_MAP_COMPARATOR);
        Map<Integer, SysMenuMap> sysMenuMapMap = new HashMap<>(0x000f);

        for (SysMenu sysMenu : sysMenuList) {
            SysMenuMap sysMenuMap = new SysMenuMap(sysMenu);
            sysMenuMapMap.put(sysMenu.getId(), sysMenuMap);
        }

        for (SysMenu sysMenu : sysMenuList) {
            Integer id = sysMenu.getId();
            Integer parentId = sysMenu.getParentId();
            SysMenuMap sysMenuMap = sysMenuMapMap.get(id);
            if(parentId == null || parentId == 0){
                sysMenuMaps.add(sysMenuMap);
                continue;
            }
            SysMenuMap parentMenuMap = sysMenuMapMap.get(parentId);
            if(!parentMenuMap.isParent()){
                parentMenuMap.setParent(true);
            }
            parentMenuMap.getSysMenuMaps().add(sysMenuMap);
        }

        return sysMenuMaps;
    }

//    /**
//     * 格式化菜单列表
//     * @param sysMenuList 菜单列表
//     * @param i 遍历的游标起始值
//     * @param sysMenuMaps 格式化的菜单列表
//     * @param sysMenuMapMap 已经含有的菜单，防重复作用
//     * @return 返回目前遍历的游标值
//     */
//    private int menuFormat(List<SysMenu> sysMenuList, int i, TreeSet<SysMenuMap> sysMenuMaps,  Map<Integer, SysMenuMap> sysMenuMapMap, Integer findParentId){
//        int sysManuListLength = sysMenuList.size();
//        for (; i < sysManuListLength ; i++) {
//            SysMenu sysMenu = sysMenuList.get(i);
//
//            Integer menuId = sysMenu.getId();
//
//            if(sysMenuMapMap.containsKey(menuId)){
//                continue;
//            }
//
//            Integer parentId = sysMenu.getParentId();
//            String  menuType = sysMenu.getMenuType();
//
//            SysMenuMap sysMenuMap = new SysMenuMap(sysMenu);
//
//            if(parentId == null || parentId == 0){
//                sysMenuMaps.add(sysMenuMap);
//                // 加入到Map里面表明已经处理过的元素
//                sysMenuMapMap.put(menuId, sysMenuMap);
//                if(findParentId != null && findParentId != 0 && findParentId.equals(menuId)){
//                    return i;
//                }
//                continue;
//            }
//
//            if(!sysMenuMapMap.containsKey(parentId)){
//                // 寻找父节点
//                // 递归
//                Integer thisFlag = menuFormat(sysMenuList, i+1, sysMenuMaps, sysMenuMapMap, parentId);
//                if(thisFlag >= sysManuListLength){
//                    continue;
//                }
//                //获取父节点
//                SysMenu thisParentMenu = sysMenuList.get(thisFlag);
//                SysMenuMap thisParentMenuMap = sysMenuMapMap.get(thisParentMenu.getId());
//                thisParentMenuMap.getSysMenuMaps().add(sysMenuMap);
//                // 加入到Map里面表明已经处理过的元素
//                sysMenuMapMap.put(menuId, sysMenuMap);
//                if(!thisFlag.equals(findParentId)){
//                    return thisFlag;
//                }
//                if(findParentId != 0){
//                    return i;
//                }
//                continue;
//            }
//
//            // 父节点在列表中
//            // 加入到Map里面表明已经处理过的元素
//            sysMenuMapMap.put(menuId, sysMenuMap);
//
//            SysMenuMap parentMenuMap = sysMenuMapMap.get(parentId);
//            parentMenuMap.getSysMenuMaps().add(sysMenuMap);
//        }
//        return i;
//    }

}
