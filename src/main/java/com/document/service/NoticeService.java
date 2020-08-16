package com.document.service;

import com.document.pojo.Notice;

import java.util.List;

public interface NoticeService {

    Notice getNoticeById(int noticeid);
    List<Notice> getReadNoticeByUser(int userid);
    List<Notice> getUnreadNoticeByUser(int userid);

    void addTeamInvitationNotice(int userid,String teamname,String leadername, int teamid);
    void addMemberNotice_join(int userid,String teamname);
    void addMemberNotice_quit(int userid,String teamname);
    void addMemberNotice_tick(int userid,String teamname,String leadername);
    void addMemberNotice_delete(int userid,String teamname);
    void addCommentNotice(int userid,int docid,String username,String title);
    void addWelcomeNotice(int userid);

    void updateNoticeStatus(int noticeid,int status);
    void deleteNotice(int noticeid);
    void deleteNoticeByTeam(int teamid);
}
