package com.xiao.login.controller;

import com.xiao.login.entity.Role;
import com.xiao.login.entity.User;
import com.xiao.login.service.SessionService;
import com.xiao.login.service.UserService;
import com.xiao.login.vo.SessionVO;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * @author xiao_elevener
 * @date 2017-12-22 14:06
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @GetMapping("/sessions")
    public List<SessionVO> getSessions() {
        Collection<Session> activeSessions = sessionService.getActiveSessions();
        return SessionVO.getSessinVOs(activeSessions);
    }

    @DeleteMapping("/session/{sessionId}")
    public void deleteSessin(@PathVariable(value = "sessionId") String sessionId, HttpServletRequest request, HttpServletResponse response){
        sessionService.deleteSession(sessionId,request,response);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/roles/{userId}")
    public List<Role> getRoles(@PathVariable(value="userId")Integer userId ){
        return  userService.findRoles(userId);
    }

    @DeleteMapping("/user_role/{userId}/{roleId}")
    public void uncorrelationRole(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "roleId") Integer roleId){
        userService.uncorrelationRoles(userId,roleId);
    }

    @PostMapping("/user_role/{userId}/{roleId}")
    public void correlationRole(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "roleId") Integer roleId){
        userService.correlationRoles(userId,roleId);
    }


}
