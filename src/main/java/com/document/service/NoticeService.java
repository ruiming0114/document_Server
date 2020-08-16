package com.document.service;

import com.document.pojo.Notice;

import java.util.List;

public interface NoticeService {

    Notice getNoticeById(int noticeid);
    List<Notice> getNoticeByUser(int userid);
    List<Notice> getUnreadNoticeByUser(int userid);

    void addTeamInvitationNotice(int userid,String teamname,String leadername, int teamid);
    void updateNoticeStatus(int noticeid,int status);
    void deleteNotice(int noticeid);
    void deleteNoticeByTeam(int teamid);
}
