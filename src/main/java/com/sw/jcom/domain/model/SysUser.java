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
import java.util.Date;

/**
 * sys_user
 * @author 
 */
@Entity
@Table(name="sys_user")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SysUser implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户名
     */
    @NotEmpty
    private String loginName;

    /**
     * 昵称
     */
    @NotEmpty
    private String userName;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 所属角色
     */
    @NotEmpty
    private Integer roleId;

    /**
     * 邮箱
     */
    private String email;

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