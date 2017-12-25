package com.xiao.login.vo;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import lombok.Data;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author xiao_elevener
 * @date 2017-12-23 23:06
 */
@Data
public class SessionVO {

    private String id;

    private Date startTimestamp;

    private Date lastAccessTime;

    private String host;


    public static List<SessionVO> getSessinVOs(Collection<Session> activeSessions){
        List<SessionVO> list=new ArrayList<>();
        for (Session session:activeSessions
             ) {
            SessionVO vo=new SessionVO();
            vo.setId((String) session.getId());
            vo.setHost(session.getHost());
            vo.setLastAccessTime(session.getLastAccessTime());
            vo.setStartTimestamp(session.getStartTimestamp());
            list.add(vo);
        }
        return  list;
    }


}
