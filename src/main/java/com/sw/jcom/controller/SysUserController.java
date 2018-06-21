package com.sw.jcom.controller;

import com.sw.jcom.common.Contents;
import com.sw.jcom.domain.entity.DataTablesInfo;
import com.sw.jcom.domain.entity.ResultEntity;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户管理
 *
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/15
 */
@Controller
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static String thisPage = "/sys/sysUser";

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/au/user")
    public String getPage() {
        return SysUserController.thisPage;
    }

    @RequestMapping("/u/user/detailPage")
    public String getUserDetailPage(Model model, HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();
        SysUser user = sysUserService.selectByUsername(username);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("email", user.getEmail());
        return "/sys/sysUser-detail";
    }

    @RequestMapping("/u/user/pwdPage")
    public String getUserPwdPage() {
        return "/sys/sysUser-pwd";
    }

    @RequestMapping("/au/user/get")
    @ResponseBody
    public SysUser getUserById(Integer userId) {
        return sysUserService.selectAdminById(userId);
    }

    @RequestMapping("/u/user/get")
    @ResponseBody
    public SysUser getOwn(HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();
        SysUser user = sysUserService.selectByUsername(username);
        return sysUserService.selectUserById(user.getId());
    }

    /**
     * 更新用户密码
     *
     * @param session
     * @param oldPassword
     * @param newPassword
     * @param newPassword2
     * @return
     */
    @PostMapping("/u/user/updatePwd")
    @ResponseBody
    public ResultEntity modifyPwd(HttpSession session, String oldPassword, String newPassword, String newPassword2) {
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword) || StringUtils.isBlank(newPassword2)) {
            return new ResultEntity(ResultEntity.Code.PASSWORD_EMPTY);
        }
        if (oldPassword.equals(newPassword)) {
            return new ResultEntity(ResultEntity.Code.PASSWORD_EQUAL);
        }
        if (!newPassword.equals(newPassword2)) {
            return new ResultEntity(ResultEntity.Code.PASSWORD_NEW_NOT_EQUAL);
        }

        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();
        if (!userDetails.getPassword().equals(oldPassword)) {
            return new ResultEntity(ResultEntity.Code.PASSWORD_NOT_EQUAL);
        }

        SysUser user = sysUserService.selectByUsername(username);

        SysUser updateUser = new SysUser();
        updateUser.setId(user.getId());
        updateUser.setPassword(newPassword);
        int updateCount = sysUserService.updateByPrimaryKeySelective(updateUser);
        if (updateCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.PASSWORD_UPDATE_ERROR);
    }

    /**
     * 更新用户昵称及邮箱
     *
     * @param session
     * @param nickname
     * @param email
     * @return
     */
    @RequestMapping("/u/user/update")
    @ResponseBody
    public ResultEntity modify(HttpSession session, String nickname, String email) {
        if (StringUtils.isBlank(nickname) || StringUtils.isBlank(email)) {
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();

        SysUser user = sysUserService.selectByUsername(username);

        SysUser updateUser = new SysUser();
        updateUser.setId(user.getId());
        updateUser.setNickname(nickname);
        updateUser.setEmail(email);
        int updateCount = sysUserService.updateByPrimaryKeySelective(updateUser);
        if (updateCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_UPDATE);
    }

    /**
     * 更新用户状态
     *
     * @param session
     * @param state
     * @return
     */
    @RequestMapping("/au/user/update")
    @ResponseBody
    public ResultEntity modify(HttpSession session, String state) {
        if (StringUtils.isBlank(state)) {
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        UserDetails userDetails = (UserDetails) session.getAttribute(Contents.SESSION_USERDETAIL);
        String username = userDetails.getUsername();

        SysUser user = sysUserService.selectByUsername(username);

        SysUser updateUser = new SysUser();
        updateUser.setId(user.getId());
        updateUser.setState(state);
        int updateCount = sysUserService.updateByPrimaryKeySelective(updateUser);
        if (updateCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_UPDATE);
    }

    /**
     * 删除用户
     *
     * @param session
     * @param username
     * @return
     */
    @RequestMapping("/au/user/delete")
    @ResponseBody
    public ResultEntity delete(HttpSession session, String username) {
        if (StringUtils.isBlank(username)) {
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        SysUser user = sysUserService.selectByUsername(username);
        int updateCount = sysUserService.deleteByPrimaryKey(user.getId());
        if (updateCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_UPDATE);
    }


    @RequestMapping("/au/user/getAll")
    @ResponseBody
    public DataTablesInfo<SysUser> getUsers(String username, String nickname, int start, int length, HttpServletRequest request) {
        return new DataTablesInfo<SysUser>(sysUserService.selectAdmin(username, nickname, start, length), request);
    }

    @RequestMapping("/au/user/getAlls")
    @ResponseBody
    public DataTablesInfo<SysUser> getAll(HttpServletRequest request) {
        return new DataTablesInfo<SysUser>(sysUserService.selectAdmin("", "", 0, 10), request);
    }
}
