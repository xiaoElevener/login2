package com.xiao.login.repository;

import com.xiao.login.entity.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void findByUid() {

        Set<UserRole> result = userRoleRepository.findByUid(1);
        assertEquals(3,result.size());

    }
}