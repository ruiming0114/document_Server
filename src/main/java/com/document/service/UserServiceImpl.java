package com.document.service;

import com.document.mapper.UserMapper;
import com.document.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public User getUserByUserId(int userid) {
        return userMapper.getUserByUserId(userid);
    }

    @Override
    public void addUser(String username, String password, String email, String wechat) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("email",email);
        map.put("wechat",wechat);
        userMapper.addUser(map);
    }

    @Override
    public void updateUser(int userid,String email, String wechat, String userimgpath) {
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("email",email);
        map.put("wechat",wechat);
        map.put("userimgpath",userimgpath);
        userMapper.updateUser(map);
    }
}
