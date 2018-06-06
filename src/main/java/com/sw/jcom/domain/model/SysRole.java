package com.sw.jcom.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * sys_role
 * @author 
 */
@Entity
@Table(name="sys_role")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SysRole implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 父角色id
     */
    private Integer parentId;

    /**
     * 排序
     */
    @NotEmpty
    private Integer order;

    /**
     * 创建时间
     */
    @NotEmpty
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 更新用户
     */
    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;


}