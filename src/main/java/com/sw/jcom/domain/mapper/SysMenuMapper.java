package com.sw.jcom.domain.mapper;

import com.sw.jcom.domain.model.SysMenu;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 删除节点
     * @param ids
     * @return
     */
    int deleteByIds(Integer[] ids);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer id);

    List<SysMenu> selectChildsByParentId(Integer id);

    List<SysMenu> selectByIds(Integer[] ids);

    List<SysMenu> selectAll();

    List<SysMenu> selectParent();

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}