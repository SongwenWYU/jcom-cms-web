package com.sw.jcom.domain.entity;

import com.sw.jcom.domain.model.SysMenu;
import lombok.*;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/10
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SysMenuMap {
    @NonNull
    private SysMenu sysMenu;
    private TreeSet<SysMenuMap> sysMenuMaps = new TreeSet<>(MENU_MAP_COMPARATOR);
    @NonNull
    private boolean parent;
    private int order;

    public SysMenuMap(){

    }

    public SysMenuMap(SysMenu sysMenu){
        this.sysMenu = sysMenu;
        this.order = sysMenu.getMenuOrder();
        this.parent = (sysMenu.getParentId() == null || sysMenu.getParentId() == 0);
    }

    public final static Comparator<SysMenuMap> MENU_MAP_COMPARATOR = new Comparator<SysMenuMap>() {
        @Override
        public int compare(SysMenuMap o1, SysMenuMap o2) {
            return o1.getOrder() - o2.getOrder();
        }
    };
}
