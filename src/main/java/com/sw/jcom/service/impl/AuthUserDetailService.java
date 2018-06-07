package com.sw.jcom.service.impl;

import com.sw.jcom.domain.dao.MyUserDetails;
import com.sw.jcom.domain.mapper.SysRoleMapper;
import com.sw.jcom.domain.mapper.SysRoleUserMapper;
import com.sw.jcom.domain.mapper.SysUserMapper;
import com.sw.jcom.domain.model.SysRole;
import com.sw.jcom.domain.model.SysRoleUser;
import com.sw.jcom.domain.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户身份认证服务类
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/6
 */
@Service("userDetailsService")
public class AuthUserDetailService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(AuthUserDetailService.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleUserMapper roleUserMapper;

    @Autowired
    private SysRoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try {
            SysUser user = userMapper.selectByUsername(username);
            if(user != null) {
                List<SysRoleUser> urs = roleUserMapper.selectByUserId(user.getId());
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for(SysRoleUser ur : urs) {
                    Integer roleId = ur.getRoleId();
                    SysRole sysRole = roleMapper.selectByPrimaryKey(roleId);
                    String role = sysRole.getRole();
                    SimpleGrantedAuthority grant = new SimpleGrantedAuthority(role);
                    authorities.add(grant);
                }
                //封装自定义UserDetails类
                userDetails = new MyUserDetails(user, authorities);
            } else {
                throw new UsernameNotFoundException("该用户不存在！");
            }
        } catch (Exception e) {
            logger.error("用户身份认证服务类异常：{}", e.getMessage());
        }
        return userDetails;
    }
}
