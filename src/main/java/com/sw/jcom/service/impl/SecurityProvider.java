package com.sw.jcom.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义认证服务
 *
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/6
 */
@Service("securityProvider")
public class SecurityProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(SecurityProvider.class);

    private UserDetailsService userDetailsService;

    public SecurityProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token
                = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        UserDetails userDetails = null;

        if (username != null) {
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        logger.info(">>>>>>>>>>用户认证<<<<<<<<<<");

        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名/密码无效");
        } else if (!userDetails.isEnabled()) {
            logger.warn("用户已被禁用");
            throw new DisabledException("用户已被禁用");
        } else if (!userDetails.isAccountNonExpired()) {
            logger.warn("账号已过期");
            throw new LockedException("账号已过期");
        } else if (!userDetails.isAccountNonLocked()) {
            logger.warn("账号已被锁定");
            throw new LockedException("账号已被锁定");
        } else if (!userDetails.isCredentialsNonExpired()) {
            logger.warn("凭证已过期");
            throw new LockedException("凭证已过期");
        }

        String password = userDetails.getPassword();
        //与authentication里面的credentials相比较
        if (!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        //授权
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //返回true后才会执行上面的authenticate方法,这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
