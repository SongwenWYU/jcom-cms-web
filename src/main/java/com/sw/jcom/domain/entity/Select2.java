package com.sw.jcom.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/7/27
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Select2 {
    private List<Result> results;
    private Paginate paginate;

    @Setter
    @Getter
    @ToString
    @EqualsAndHashCode
    static class Result {

        private Integer id;
        private String text;
        private Boolean selected;
        private Boolean disabled;

        private List<Result> children;
    }

    @Setter
    @Getter
    @ToString
    @EqualsAndHashCode
    static class Paginate {
        private Boolean more;
    }

}


