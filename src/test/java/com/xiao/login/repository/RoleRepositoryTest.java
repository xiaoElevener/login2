package com.xiao.login.repository;

import com.xiao.login.entity.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findById() {
        Role role = roleRepository.findById(1);
        assertNotNull(role);
    }

    @Test
    public void saveRole(){
        Role role =new Role();
        role.setName("fan");
        role.setType("user");
        Role result = roleRepository.save(role);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteRole(){
        Role role = roleRepository.findById(5);
        roleRepository.delete(role);
    }
}