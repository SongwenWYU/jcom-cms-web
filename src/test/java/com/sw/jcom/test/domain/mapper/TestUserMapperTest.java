package com.sw.jcom.test.domain.mapper;

import com.sw.jcom.BaseTestAbstract;
import com.sw.jcom.domain.model.TestUser;
import com.sw.jcom.service.TestUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author songwen
 * Created on 2018/6/2
 */
public class TestUserMapperTest extends BaseTestAbstract {
    
    @Autowired
    private TestUserService testUserService;

    @Test
    public void search() {
        List<TestUser> list = testUserService.searchList();
        System.out.println(list.size());
    }

    @Test
    public void searchLimit() {
        List<TestUser> list = testUserService.searchLimit();
        System.out.println(list.size());
    }
}
