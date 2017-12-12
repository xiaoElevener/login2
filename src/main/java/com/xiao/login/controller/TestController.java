package com.xiao.login.controller;

/**
 * @author Administrator
 * @create 2017-12-12 16:55
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

import static java.lang.System.out;

@Controller
public class TestController {
    @RequestMapping("/index")
    public String index(Map<String, Object> model){
        model.put("name", "adam");
        out.println("ss的值是:---" + ",当前方法=TestController,index()");
        return "test";//返回的内容就是templetes下面文件的名称
    }
}