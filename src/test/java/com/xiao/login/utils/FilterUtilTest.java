package com.xiao.login.utils;

import org.junit.Test;

import java.util.LinkedHashMap;

import static java.lang.System.out;

public class FilterUtilTest {
    @Test
    public void loadFromResource() {
        LinkedHashMap<String, String> map = null;
        try {
            map = FilterUtil.loadFromProperties("shiro-filterUrl.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("map的值是:---" + map + ",当前方法=FilterUtilTest,loadFromResource()");
    }
}