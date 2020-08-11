package com.document.service;

import com.document.pojo.User;

import java.util.Map;

public interface UserService {

    User getUserByUserName(String username);
    User getUserByUserId(int userid);
    void addUser(String username,String password,String email,String wechat);
    void updateUser(int userid,String email,String wechat,String userimgpath);
}
