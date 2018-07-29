package com.sw.jcom.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * pro_classify
 * @author 
 */
@Entity
@Table(name="pro_classify")
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ProClassify implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 分类名称
     */
    @NotEmpty
    private String classifyName;

    /**
     * 所属父类id
     */
    private Integer parentId;

    /**
     * 状态。-1：归档，1：未归档
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 修改人
     */
    private Integer gmtUserId;

    private static final long serialVersionUID = 1L;

}