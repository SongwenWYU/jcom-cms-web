package com.sw.jcom.controller;

import com.sw.jcom.common.exception.JcomException;
import com.sw.jcom.domain.entity.ResultEntity;
import com.sw.jcom.domain.model.ProClassify;
import com.sw.jcom.domain.model.SysUser;
import com.sw.jcom.service.ProClassifyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/26
 */
@Controller
@RequestMapping("/u/pro/classify")
public class ProClassifyController extends BaseController{

    @Autowired
    private ProClassifyService proClassifyService;

    private String page = "/pro/pro-classify.html";

    @RequestMapping("page")
    public String getPage() {
        return page;
    }

    /**
     * 查询用户下的分类
     * @param id 父节点ID
     * @param status 状态
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("select")
    public List<ProClassify> select(Integer id, Integer[] status, HttpSession httpSession) throws JcomException {
        SysUser user = getUser(httpSession);
        return proClassifyService.selectChildrens(id, status, user.getId());
    }

    /**
     * 查询某个分类
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("selectById")
    public ProClassify select(Integer id, HttpSession httpSession) throws JcomException {
        SysUser user = getUser(httpSession);
        return proClassifyService.selectById(id, user.getId());
    }

    @ResponseBody
    @PostMapping("update")
    public ResultEntity update(ProClassify proClassify, HttpSession httpSession) throws JcomException {
        SysUser user = getUser(httpSession);
        if(proClassify.getId() == null || StringUtils.isBlank(proClassify.getClassifyName())
                || proClassify.getParentId() == null){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }

        if (proClassify.getStatus() != 1 && proClassify.getStatus() != -1){
            return new ResultEntity(ResultEntity.Code.ERROR_STATE);
        }

        ProClassify queryProClassify = proClassifyService.selectById(proClassify.getId(), user.getId());
        queryProClassify.setParentId(proClassify.getParentId());
        queryProClassify.setClassifyName(proClassify.getClassifyName());
        queryProClassify.setGmtModified(LocalDateTime.now());
        queryProClassify.setGmtUserId(user.getId());
        int updateCount = proClassifyService.update(queryProClassify, user.getId());
        if (updateCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_UPDATE);
    }

    @ResponseBody
    @PostMapping("updateStatus")
    public ResultEntity updateStatus(int id, int status, HttpSession httpSession) throws JcomException {
        if(status != 1 && status != -1){
            return new ResultEntity(ResultEntity.Code.ERROR_STATE);
        }
        SysUser user = getUser(httpSession);
        int updateCount = proClassifyService.updateStatus(id, status, user.getId());
        if (updateCount == 1) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_UPDATE);
    }

    @ResponseBody
    @PostMapping("add")
    public ResultEntity insert(ProClassify proClassify, HttpSession httpSession) throws JcomException {
        if(StringUtils.isBlank(proClassify.getClassifyName())){
            return new ResultEntity(ResultEntity.Code.ERROR_EMPTY);
        }
        if (proClassify.getStatus() != 1 && proClassify.getStatus() != -1){
            return new ResultEntity(ResultEntity.Code.ERROR_STATE);
        }
        SysUser user = getUser(httpSession);
        if (proClassify.getParentId() != null){
            ProClassify parent = proClassifyService.selectById(proClassify.getParentId(), user.getId());
            if (parent == null || !user.getId().equals(parent.getGmtUserId())) {
                return new ResultEntity(ResultEntity.Code.ERROR_ADD.getCode(), "父类异常");
            }
        }
        proClassify.setGmtUserId(user.getId());
        proClassify.setGmtCreate(LocalDateTime.now());
        proClassify.setGmtModified(LocalDateTime.now());
        int count = proClassifyService.add(proClassify);
        if (count > 0) {
            return new ResultEntity(ResultEntity.Code.OK);
        }
        return new ResultEntity(ResultEntity.Code.ERROR_ADD);
    }


}
