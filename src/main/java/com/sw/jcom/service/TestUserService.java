package com.sw.jcom.service;

import com.sw.jcom.domain.model.TestUser;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/2
 */
public interface TestUserService {
    List<TestUser> searchList();
    List<TestUser> searchLimit();
    TestUser searchFromRespository(int id);
}
