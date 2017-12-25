package com.xiao.login.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @author xiao_elevener
 * @date 2017-12-21 23:36
 */
@Slf4j
public class MySessionListener implements SessionListener {


    @Override
    public void onStart(Session session) {
      log.info("【MySessionListener】会话:{}开启",session.getId());
    }

    @Override
    public void onStop(Session session) {
        log.info("【MySessionListener】会话关闭",session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        log.info("【MySessionListener】会话:{}过期,",session.getId());
    }
}
