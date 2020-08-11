package com.document.service;

import org.springframework.stereotype.Service;

import java.util.Map;


public interface PermsUtilService {

    //添加privatePerms
    void addPerms(Map<String,Object> map);
    //添加teamPerms
    void addPermsOfTeam(Map<String,Object> map);
    //修改privatePerms
    void updatePerms(Map<String,Object> map);
    //修改privatePerms
    void updatePermsOfTeam(Map<String,Object> map);


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
