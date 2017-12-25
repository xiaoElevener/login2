package com.xiao.login.utils;


import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;


/**
 * @author xiao_elevener
 * @date 2017-12-19 15:41
 */
public class FilterUtil {
    /**
     * 将properties文件转化为LinkHashMap
     * @param fileAddr classPath
     * @return LinkedHashMap
     * @throws IOException
     */
    public static LinkedHashMap<String,String> loadFromProperties(String fileAddr) throws IOException {
        OrderedProperties properties=new OrderedProperties();
        properties.load(ClassLoader.getSystemResourceAsStream(fileAddr));
        return properties.getLinkedMap();
    }
}
