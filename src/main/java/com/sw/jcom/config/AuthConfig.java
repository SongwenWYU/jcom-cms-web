package com.sw.jcom.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义身份验证类（用于重写WebSecurityConfigurerAdapter默认配置）
 *
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/5
 */
@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(AuthConfig.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationProvider securityProvider;

    @Override
    protected UserDetailsService userDetailsService() {
        //自定义用户信息类
        return this.userDetailsService;
    }

    /**
     * 重写该方法，添加自定义用户
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义AuthenticationProvider
        auth.authenticationProvider(securityProvider);
    }

    /**
     * 重写该方法，设定用户访问权限
     * 用户身份可以访问 订单相关API
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //用户权限
                .antMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
                //管理员权限
                .antMatchers("/users/**").hasRole("ADMIN")
                .and()
                .formLogin()
                //跳转登录页面的控制器，该地址要保证和表单提交的地址一致！
                .loginPage("/login")
                //成功处理
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
                            throws IOException, ServletException {
                        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        if (principal != null && principal instanceof UserDetails) {
                            UserDetails user = (UserDetails) principal;
                            logger.info(">>>>>>>>>>登录用户:{}<<<<<<<<<<", user.getUsername());
                            //维护在session中
                            arg0.getSession().setAttribute("userDetail", user);
                            arg1.sendRedirect("/");
                        }
                    }
                })
                //失败处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException)
                            throws IOException, ServletException {
                        logger.error(">>>>>>>>>>登录出错:{}<<<<<<<<<<", authenticationException.getMessage());
                        HttpSession session = request.getSession(false);

                        if (session != null) {
                            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
                                    authenticationException);
                        }
                        response.sendRedirect("/login?error");
                    }
                })
                .permitAll()
                .and()
                .logout()
                .permitAll();
//                .and()
//                暂时禁用CSRF，否则无法提交表单
//                .csrf().disable();
    }
}
