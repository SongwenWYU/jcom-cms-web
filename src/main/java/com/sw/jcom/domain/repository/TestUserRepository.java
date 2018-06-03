package com.sw.jcom.domain.repository;

import com.sw.jcom.domain.model.TestUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author songwen
 * gmail: zero.hero.he@gmail.com
 * Created on 2018/6/2
 */
@Repository
public interface TestUserRepository extends
        Serializable,
        JpaRepository<TestUser, Integer>,
        JpaSpecificationExecutor<TestUser> {
}
