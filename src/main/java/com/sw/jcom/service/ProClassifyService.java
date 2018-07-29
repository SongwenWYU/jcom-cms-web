package com.sw.jcom.service;

import com.sw.jcom.domain.model.ProClassify;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/26
 */
public interface ProClassifyService {

    ProClassify selectById(int id);

    List<ProClassify> selectChildrens(int id, Integer[] status);

    int add(ProClassify proClassify);

    int updateStatus(int id, int status, int userId);

    int update(ProClassify proClassify, int userId);

}
