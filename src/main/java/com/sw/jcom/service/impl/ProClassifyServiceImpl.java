package com.sw.jcom.service.impl;

import com.sw.jcom.domain.mapper.ProClassifyMapper;
import com.sw.jcom.domain.model.ProClassify;
import com.sw.jcom.service.ProClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/26
 */
@Service
public class ProClassifyServiceImpl implements ProClassifyService {

    @Autowired
    private ProClassifyMapper proClassifyMapper;


    @Override
    public ProClassify selectById(int id, Integer userId) {
        return proClassifyMapper.selectByPrimaryKey(id, userId);
    }

    @Override
    public List<ProClassify> selectChildrens(int id, Integer[] status, Integer userId) {
        return proClassifyMapper.selectChildrens(id, status, userId);
    }

    @Override
    public int add(ProClassify proClassify) {
        return proClassifyMapper.insert(proClassify);
    }

    @Override
    public int updateStatus(int id, int status, int userId) {
        ProClassify proClassify = selectById(id, userId);
        if (proClassify == null || proClassify.getId() == null){
            return 0;
        }
        proClassify.setGmtUserId(userId);
        proClassify.setStatus(status);
        proClassify.setGmtModified(LocalDateTime.now());
        return proClassifyMapper.updateByPrimaryKeySelective(proClassify, userId);
    }

    @Override
    public int update(ProClassify proClassify, int userId) {
        proClassify.setGmtUserId(userId);
        proClassify.setGmtModified(LocalDateTime.now());
        return proClassifyMapper.updateByPrimaryKeySelective(proClassify, userId);
    }
}
