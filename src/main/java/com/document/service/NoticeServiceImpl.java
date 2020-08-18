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
    UserService userService;

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Notice getNoticeById(int noticeid) {
        return noticeMapper.getNoticeById(noticeid);
    }

    @Override
    public List<Notice> getReadNoticeByUser(int userid) {
        return noticeMapper.getReadNoticeByUser(userid);
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
    public void addMemberNotice_join(int userid, String teamname) {
        Map<String,Object> map = new HashMap<>();
        String title = "加入团队成功";
        String content = "您已成功加入["+teamname+"]团队！";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addMemberNotice_quit(int userid, String teamname) {
        Map<String,Object> map = new HashMap<>();
        String title = "退出团队成功";
        String content = "您已成功退出["+teamname+"]团队！";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addMemberNotice_tick(int userid, String teamname,String leadername) {
        Map<String,Object> map = new HashMap<>();
        String title = "移出团队通知";
        String content = "您已被团队管理员["+leadername+"]移出团队["+teamname+"]。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addMemberNotice_delete(int userid, String teamname) {
        Map<String,Object> map = new HashMap<>();
        String title = "解散团队通知";
        String content = "您所在的团队["+teamname+"]已被团队管理员解散。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addCommentNotice(int userid, int docid, String username, String doctitle) {
        Map<String,Object> map = new HashMap<>();
        String title = "来自文档["+doctitle+"]的新评论";
        String content = "用户["+username+"]刚刚评论了您的文档["+doctitle+"]。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",3);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",docid);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addWelcomeNotice(int userid) {
        Map<String,Object> map = new HashMap<>();
        String title = "注册成功";
        String content = "["+userService.getUserByUserId(userid).getUsername()+"]，你好！欢迎使用金刚石文档，使用过程中如遇问题请点击帮助中心获取帮助。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addDeleteDocNotice(int userid,String doctitle) {
        Map<String,Object> map = new HashMap<>();
        String title = "删除文档通知";
        String content = "您的文档["+doctitle+"]已经删除，如需恢复请到回收站操作。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addRecoverDocNotice(int userid, String doctitle) {
        Map<String,Object> map = new HashMap<>();
        String title = "恢复文档通知";
        String content = "您的文档["+doctitle+"]已经从回收站中恢复。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",-1);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addCooperateNotice(int userid, String doctitle, String author, int docid) {
        Map<String,Object> map = new HashMap<>();
        String title = "新的文档协作邀请";
        String content = "用户["+author+"]邀请您参与协作文档["+doctitle+"]，请确认是否同意协作？";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",4);
        map.put("addtime",addTime);
        map.put("teamid",-1);
        map.put("docid",docid);
        noticeMapper.addNotice(map);
    }

    @Override
    public void addCooperateNotice_agree(int userid, String doctitle, String author) {
        Map<String,Object> map = new HashMap<>();
        String title = "参与协作成功";
        String content = "您接受了用户["+author+"]的邀请，参与协作文档["+doctitle+"]，文档内容可在收件箱中详细查看。";
        Timestamp addTime = new Timestamp(System.currentTimeMillis());
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        map.put("status",0);
        map.put("type",2);
        map.put("addtime",addTime);
        map.put("teamid",-1);
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
