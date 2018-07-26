package com.sw.jcom.domain.mapper;

import com.sw.jcom.domain.model.ProClassify;

public interface ProClassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProClassify record);

    int insertSelective(ProClassify record);

    ProClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProClassify record);

    int updateByPrimaryKey(ProClassify record);
}