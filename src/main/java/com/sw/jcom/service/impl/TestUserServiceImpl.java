package com.sw.jcom.service.impl;

import com.sw.jcom.domain.mapper.TestUserMapper;
import com.sw.jcom.domain.model.TestUser;
import com.sw.jcom.domain.model.TestUserExample;
import com.sw.jcom.domain.repository.TestUserRepository;
import com.sw.jcom.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/2
 */
@Service
@Transactional
public class TestUserServiceImpl implements TestUserService {

    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private TestUserRepository testRepository;


    @Override
    public List<TestUser> searchList() {
        TestUserExample testUserExample = new TestUserExample();
        return testUserMapper.selectByExample(testUserExample);
    }
    @Override
    public List<TestUser> searchLimit() {
        TestUserExample testUserExample = new TestUserExample();
        testUserExample.setLimit(2);
        testUserExample.setOffset(1);
        return testUserMapper.selectByExample(testUserExample);
    }

    @Override
    public TestUser searchFromRespository(int id) {
        return testRepository.findById(id).get();
    }
}