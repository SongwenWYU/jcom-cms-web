package com.sw.jcom.domain.mapper;

import com.sw.jcom.domain.model.ProClassify;

import java.util.List;

public interface ProClassifyMapper {
    int deleteByPrimaryKey(Integer id, Integer userId);

    int insert(ProClassify record);

    int insertSelective(ProClassify record);

    ProClassify selectByPrimaryKey(Integer id, Integer userId);

    List<ProClassify> selectChildrens(Integer id, Integer[] status, Integer userId);

    int updateByPrimaryKeySelective(ProClassify record, Integer userId);

    int updateByPrimaryKey(ProClassify record, Integer userId);
}