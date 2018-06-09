package com.sw.jcom.domain.mapper;

import com.sw.jcom.domain.model.SysMenuRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface SysMenuRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    SysMenuRole selectByPrimaryKey(Integer id);

    List<SysMenuRole> selectByRoleId(Integer roleId);

    int updateByPrimaryKeySelective(SysMenuRole record);

    int updateByPrimaryKey(SysMenuRole record);
}