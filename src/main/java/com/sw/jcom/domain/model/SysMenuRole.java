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
import java.sql.Timestamp;

/**
 * sys_menu_role
 * @author 
 */
@Entity
@Table(name="sys_menu_role")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SysMenuRole implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 所属角色
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    private Timestamp gmtModified;

    /**
     * 更新人
     */
    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;
}