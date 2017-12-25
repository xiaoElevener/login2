package com.xiao.login.service.impl;

import com.xiao.login.Enum.ResultEnum;
import com.xiao.login.exception.LoginException;
import com.xiao.login.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author xiao_elevener
 * @date 2017-12-22 23:51
 */
@Service
@Slf4j
public class SessionServiceImpl implements SessionService{

    public static final String SHIRO_COOKIE_NAME="shiro_cookie";

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public Collection<Session> getActiveSessions() {
        return sessionDAO.getActiveSessions();
    }

    /**
     * 删除session的时候注意删除的是不是当前用户的session，如果是顺便得删除下cookie中的sessionId
     * @param sessionId sessionId
     */
    @Override
    public void deleteSession(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        Session session = sessionDAO.readSession(sessionId);
        sessionDAO.delete(session);
    }
}
