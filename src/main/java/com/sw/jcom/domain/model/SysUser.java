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

    /**
     * 账号国企
     */
    public final static String STATE_ACCOUNTEXPIRED = "STATE_ACCOUNTEXPIRED";
    /**
     * 账号锁定
     */
    public final static String STATE_LOCK = "STATE_LOCK";
    /**
     * TOKEN过期
     */
    public final static String STATE_TOKENEXPIRED = "STATE_TOKENEXPIRED";
    /**
     * 账号正常
     */
    public final static String STATE_NORMAL = "STATE_NORMAL";

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String state;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
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