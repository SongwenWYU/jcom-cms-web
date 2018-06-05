package com.sw.jcom.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/5
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Value("${system.name}")
    private String name;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "用户名或密码错误");
        }

        if (error != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                AuthenticationException ex = (AuthenticationException) session
                        .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
                model.addAttribute("error", ex != null ? ex.getMessage() : "none");
            }
        }

        renderHiddenInputs(model, request);
        model.addAttribute("title", name);
        return "login";
    }

    private void renderHiddenInputs(Model model, HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        if (token != null) {
            model.addAttribute("_cscf", token);
        }
    }
}
