package com.sw.jcom.controller;

import com.sw.jcom.common.Contents;
import com.sw.jcom.domain.entity.ResultEntity;
import com.sw.jcom.domain.entity.SysMenuMap;
import com.sw.jcom.domain.model.SysMenu;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.SysMenuService;
import com.sw.jcom.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/25
 */
@Controller
public class SysMenuController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String thisPage = "/sys/sysMenu";

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/au/menu")
    public String getPage(){
        return SysMenuController.thisPage;
    }

    @PostMapping("/au/menu/getAll")
    @ResponseBody
    public TreeSet<SysMenuMap> getAllMenu(){
        List<SysMenu> sysMenuList = sysMenuService.selectAll();
        return sysMenuService.menuFormat(sysMenuList);
    }

    @PostMapping("/au/menu/update")
    @ResponseBody
    public ResultEntity update(SysMenu sysMenu){
        if(StringUtils.isBlank(sysMenu.getMenuType()) || StringUtils.isBlank(sysMenu.getManuName())){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        int count = sysMenuService.updateByPrimaryKeySelective(sysMenu);
        if (count > 0) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_UPDATE);
    }

    @PostMapping("/au/menu/delete")
    @ResponseBody
    public ResultEntity delete(Integer id){
        if(id == null){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        // 校验删除的菜单是否有子菜单
        if(sysMenuService.selectChildsByParentId(id).size() > 0){
            return new ResultEntity(ResultEntity.Code.MENU_HAVA_CHILD);
        }

        int deleteCount = sysMenuService.deleteByPrimaryKey(id);
        if (deleteCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_DELETE);
    }

    /**
     * 删除菜单，包括子菜单
     * @param id
     * @return
     */
    @PostMapping("/au/menu/delete/all")
    @ResponseBody
    public ResultEntity deleteAll(Integer id){
        if(id == null){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        // 查询所有子菜单
        List<SysMenu> sysMenuList = sysMenuService.selectChildsByParentId(id);
        ListIterator<SysMenu> sysMenuIterator = sysMenuList.listIterator();
        List<Integer> idList = new ArrayList<>(0x00ff);
        while (sysMenuIterator.hasNext()){
            SysMenu sysMenu = sysMenuIterator.next();
            Integer menuId = sysMenu.getId();
            idList.add(menuId);
            if(sysMenu.getParent() == 1){
                List<SysMenu> chidMenuList = sysMenuService.selectChildsByParentId(menuId);
                for (SysMenu childMenu : chidMenuList) {
                    idList.add(childMenu.getId());
                    if(childMenu.getParent() == 1){
                        sysMenuIterator.add(childMenu);
                    }
                }
            }
        }
        // 删除所有菜单
        Integer[] ids = new Integer[idList.size()];
        ids = idList.toArray(ids);
        int deleteCount = sysMenuService.deleteByIds(ids);
        if (deleteCount > 0) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_DELETE);
    }

    @PostMapping("/au/menu/add")
    @ResponseBody
    public ResultEntity add(SysMenu sysMenu){
        if(StringUtils.isBlank(sysMenu.getMenuType()) || StringUtils.isBlank(sysMenu.getManuName())){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }

        int addCount = sysMenuService.insert(sysMenu);
        if (addCount > 0) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_ADD);
    }

    /**
     * 上下移动菜单
     * @param id
     * @param oId
     * @return
     */
    @PostMapping("/au/menu/add")
    @ResponseBody
    public ResultEntity order(Integer id, Integer oId, HttpSession httpSession){
        if(id == null || oId == null){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        UserDetails userDetails = (UserDetails) httpSession.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();
        SysUser user = sysUserService.selectByUsername(username);
        SysMenu sysMenu = sysMenuService.selectByPrimaryKey(id);
        SysMenu sysMenuO = sysMenuService.selectByPrimaryKey(oId);
        Integer order = sysMenu.getMenuOrder();
        Integer oOrder = sysMenuO.getMenuOrder();
        sysMenu.setMenuOrder(oOrder);
        sysMenuO.setMenuOrder(order);
        Date now = new Date();
        sysMenu.setGmtModified(now);
        sysMenuO.setGmtModified(now);
        sysMenu.setGmtUserId(user.getId());
        sysMenuO.setGmtUserId(user.getId());

        int updateCount = sysMenuService.updateByPrimaryKeySelective(sysMenu);
        updateCount += sysMenuService.updateByPrimaryKeySelective(sysMenuO);

        if (updateCount > 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_ADD);
    }
}
