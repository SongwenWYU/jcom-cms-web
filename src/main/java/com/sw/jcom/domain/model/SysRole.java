package com.sw.jcom.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * sys_role
 *
 * @author
 */
@Entity
@Table(name = "sys_role")
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
    private String role;

    /**
     * 名称
     */
    private String roleName;

    /**
     * 父角色id
     */
    private Integer parentId;

    /**
     * 排序
     */
    @NotEmpty
    private Integer roleOrder;

    /**
     * 创建时间
     */
    @NotEmpty
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    private Timestamp gmtModified;

    /**
     * 更新用户
     */
    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;
}