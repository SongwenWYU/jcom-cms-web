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

    List<SysUser> selectAdmin(@Param("username")String username, @Param("nickname")String nickname);

    /**
     * 管理员查询使用
     * @param id
     * @return
     */
    SysUser selectAdminById(Integer id);

    /**
     * 普通用户查询个人信息使用
     * @param id
     * @return
     */
    SysUser selectUserById(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}