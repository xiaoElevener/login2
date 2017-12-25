package com.xiao.login.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

public interface SessionService {


    Collection<Session> getActiveSessions();

    void deleteSession(String sessionId, HttpServletRequest request,HttpServletResponse response);

}
