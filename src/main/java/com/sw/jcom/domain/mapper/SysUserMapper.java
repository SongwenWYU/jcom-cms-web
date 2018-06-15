package com.sw.jcom.domain.mapper;

import com.sw.jcom.domain.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    List<SysUser> selectAll();

    List<SysUser> select(@Param("username")String username, @Param("nickname")String nickname);

    SysUser selectByUsername(String username);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}