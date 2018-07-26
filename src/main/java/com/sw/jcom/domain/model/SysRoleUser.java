package com.sw.jcom.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * sys_role_user
 * @author 
 */
@Entity
@Table(name="sys_role_user")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SysRoleUser implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer roleId;

    private Integer userId;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;
}