package com.sw.jcom.service.impl;

import com.sw.jcom.domain.mapper.ProClassifyMapper;
import com.sw.jcom.domain.model.ProClassify;
import com.sw.jcom.service.ProClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> map = new HashMap<>(0x04);
        map.put("id", id);
        map.put("status", status);
        map.put("userId", userId);
        return proClassifyMapper.selectChildrens(map);
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
