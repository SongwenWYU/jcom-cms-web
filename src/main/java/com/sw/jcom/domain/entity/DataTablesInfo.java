package com.sw.jcom.domain.entity;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/17
 */
@Setter
@Getter
public class DataTablesInfo<T> {
    private Integer draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;
    private long start;
    private long length;

    public DataTablesInfo(){

    }

    public DataTablesInfo(PageInfo<T> pageInfo, HttpServletRequest request){
        if(StringUtils.isNotBlank(request.getParameter("draw"))){
            this.draw = Integer.parseInt(request.getParameter("draw"));
        }
        this.recordsTotal = pageInfo.getTotal();
        this.recordsFiltered = recordsTotal;
        this.data = pageInfo.getList();
        this.start = pageInfo.getStartRow();
        this.length = pageInfo.getPageSize();
    }

}
