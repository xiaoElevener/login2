package com.xiao.login.dao;

import com.xiao.login.base.TestBase;
import com.xiao.login.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static java.lang.System.out;
import static org.junit.Assert.*;


public class UserMapperTest extends TestBase{

    @Autowired
    private UserMapper mapper;

    @Test
    public void saveUser() throws Exception {
        mapper.saveUser(new User(1,"xiao","12@163.com","123456",new Date(),new Date(),1));
    }

    @Test
    public void deleteUser() throws Exception {
        mapper.deleteUser(1);
    }

    @Test
    public void updateUser() throws Exception {
        mapper.updateUser(new User(1,"xiao","12345@163.com","123456",new Date(),new Date(),1));
    }

    @Test
    public void getUser() throws Exception {
        out.println(mapper.getUser(1));
    }

    @Test
    public void getUser2(){
        out.println(mapper.getUserByUserName("xiao"));
    }

    @Test
    public void getRoles() throws  Exception{
        out.println("【roles】="+mapper.getRolesByUserName("xiao"));
    }

    @Test
    public void getPermissions(){
        out.println("【roles】="+mapper.getPermissions("xiao"));
    }

    @Test
    public void getAllUsers(){
        out.println(mapper.getAllUsers());
    }

    @Test
    public void getRolesByUserId(){
        out.println(mapper.getRolesByUserId(1));
    }

}