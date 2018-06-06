package com.sw.jcom.domain.dao;

import com.sw.jcom.domain.model.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/6
 */
public class MyUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private SysUser user;
    /**
     * 用户角色
     */
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(SysUser user, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.user.getState().equals(SysUser.STATE_ACCOUNTEXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.user.getState().equals(SysUser.STATE_LOCK);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.user.getState().equals(SysUser.STATE_TOKENEXPIRED);
    }

    @Override
    public boolean isEnabled() {
        return this.user.getState().equals(SysUser.STATE_NORMAL);
    }
}
