package com.xiao.login.utils;

import org.junit.Test;

import java.io.FileReader;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class OrderedPropertiesTest {
    @Test
    public void getMap() throws Exception {

        OrderedProperties properties = new OrderedProperties();
        properties.load(new FileReader("G:\\IDEA_Project\\login2\\src\\main\\resources\\shiro-filterUrl.properties"));
        out.println(properties.keySet());
        out.println("properties.getLinkedMap的值是:---" + properties.getLinkedMap() + ",当前方法=OrderedPropertiesTest,getMap()");

    }

}