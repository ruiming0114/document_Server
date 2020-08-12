package com.document.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface PermsUtilService {
    //查询个人权限
    int queryPerms(int docid,int userid);
    //查询队内权限
    int queryTeamPerms(int teamid, int userid);
    //添加个人权限
    void addPerms(int docid,int userid,int privateperms);
    //添加队内权限
    void addTeamPerms(int teamid, int userid,int teamperms);
    //修改个人权限
    void updatePerms(int docid,int userid,int privateperms);
    //修改队内权限
    void updateTeamPerms(int teamid, int userid,int teamperms);
    //删除个人权限
    void deletePerms(int docid,int userid);
    //删除队内权限
    void deletePermsOfTeam(int teamid, int userid);

    //判断总权限
    int returnPerms(int docid,int userid);

    //文章--可读？
    boolean canRead(int docid,int userid);

    //文章--可评论？
    boolean canComment(int docid,int userid);

    //文章--可写？
    boolean canWrite(int docid,int userid);

    //文章--可删？
    boolean canDelete(int docid,int userid);

}
