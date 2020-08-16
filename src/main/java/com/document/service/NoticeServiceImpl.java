package com.document.service;

import com.document.mapper.NoticeMapper;
import com.document.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Notice getNoticeById(int noticeid) {
        return noticeMapper.getNoticeById(noticeid);
    }

    @Override
    public List<Notice> getNoticeByUser(int userid) {
        return noticeMapper.getNoticeByUser(userid);
    }

    @Override
    public List<Notice> getUnreadNoticeByUser(int userid) {
        return noticeMapper.getUnreadNoticeByUser(userid);
    }

    @Override
    public void addTeamInvitationNotice(int userid, String teamname, String leadername, int teamid) {
        Map<String,Object> map = new HashMap<>();
        String title = "来自["+teamname+"]团队的邀请";
        String content = "用户["+leadername+"]邀请你加入团队["+teamname+"]，请确认是否同意加入该团队？";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",1);
        map.put("addtime",addTime);
        map.put("teamid",teamid);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void updateNoticeStatus(int noticeid,int status) {
        Map<String,Object> map = new HashMap<>();
        map.put("noticeid",noticeid);
        map.put("status",status);
        noticeMapper.updateNoticeStatus(map);
    }

    @Override
    public void deleteNotice(int noticeid) {
        if (noticeMapper.getNoticeById(noticeid).getType()!=1||noticeMapper.getNoticeById(noticeid).getStatus()>1){
            noticeMapper.deleteNotice(noticeid);
        }
    }

    @Override
    public void deleteNoticeByTeam(int teamid) {
        noticeMapper.deleteNoticeByTeam(teamid);
    }
}
