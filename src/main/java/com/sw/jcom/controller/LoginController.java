package com.sw.jcom.controller;

import com.sw.jcom.common.Contents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
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
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute(Contents.SESSION_USERDETAIL) != null){
            return "redirect:/home";
        }
        if (error != null && session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            model.addAttribute("error", ex != null ? ex.getMessage() : "none");
        }

        model.addAttribute("title", name);
        return "login";
    }

}
