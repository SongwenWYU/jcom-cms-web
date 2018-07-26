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

    private Timestamp gmtCreate;

    private Timestamp gmtModified;

    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;
}