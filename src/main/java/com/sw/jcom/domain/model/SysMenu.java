package com.sw.jcom.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * sys_menu
 * @author 
 */
@Entity
@Table(name="sys_menu")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SysMenu implements Serializable {

    /**
     * 页面
     */
    public final static String TYPE_PAGE = "TYPE_PAGE";
    /**
     * 下拉
     */
    public final static String TYPE_DROPDOWN  = "TYPE_DROPDOWN";

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 类型
     */
    private String menuType;

    /**
     * 路径
     */
    private String url;

    /**
     * 名称
     */
    private String manuName;

    /**
     * 排序
     */
    private Integer menuOrder;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 是否为父节点
     */
    private Byte parent;

    /**
     * 是否新标签页打开
     */
    private Byte blank;

    /**
     * 图标
     */
    private String cssIcon;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 更新人
     */
    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;
}