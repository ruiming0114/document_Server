package com.document.service;

import com.document.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUserByUserName(String username);
    User getUserByUserId(int userid);
    void addUser(String username,String password,String email,String wechat,String intro,String question,String answer);
    void updateUserInfo(int userid,String email,String wechat,String intro,String question,String answer);
    void updateUserImage(int userid,String userimgpath);
    void updateUserPwd(int userid,String password);

    List<Map<String,Object>> getRecentReadDoc(int userid);
    List<Map<String,Object>> getCollectedDoc(int userid);
    List<Map<String,Object>> getMyDoc(int userid);
    List<Map<String,Object>> getMyDeleteDoc(int userid);
    List<Map<String,Object>> getCreatedTeam(int userid);
    List<Map<String,Object>> getJoinedTeam(int userid);
    List<Map<String,Object>> getOthersCooperateDoc(int userid);
    List<Map<String,Object>> getMyCooperateDoc(int userid);
}
