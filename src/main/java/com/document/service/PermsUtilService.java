package com.document.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface PermsUtilService {
    //查询个人权限
    int queryPerms(Map<String,Object> map);
    //查询队内权限
    int queryPermsOfTeam(Map<String,Object> map);
    //添加个人权限
    void addPerms(Map<String,Object> map);
    //添加队内权限
    void addPermsOfTeam(Map<String,Object> map);
    //修改个人权限
    void updatePerms(Map<String,Object> map);
    //修改队内权限
    void updatePermsOfTeam(Map<String,Object> map);
    //删除个人权限
    void deletePerms(Map<String,Object> map);
    //删除队内权限
    void deletePermsOfTeam(Map<String,Object> map);


    //个人文章--可读？
    boolean canRead(Map<String,Object> map);
    //团队文章--可读？
    boolean canReadOfTeam(Map<String,Object> map);

    //个人文章--可评论？
    boolean canComment(Map<String,Object> map);
    //团队文章--可评论？
    boolean canCommentOfTeam(Map<String,Object> map);

    //个人文章--可写？
    boolean canWrite(Map<String,Object> map);
    //团队文章--可写？
    boolean canWriteOfTeam(Map<String,Object> map);

}
