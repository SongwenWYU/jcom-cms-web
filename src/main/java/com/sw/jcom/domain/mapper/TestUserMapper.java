package com.sw.jcom.domain.mapper;

import com.sw.jcom.domain.model.TestUser;
import com.sw.jcom.domain.model.TestUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestUserMapper {
    long countByExample(TestUserExample example);

    int deleteByExample(TestUserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    List<TestUser> selectByExample(TestUserExample example);

    TestUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") TestUser record, @Param("example") TestUserExample example);

    int updateByExample(@Param("record") TestUser record, @Param("example") TestUserExample example);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);
}