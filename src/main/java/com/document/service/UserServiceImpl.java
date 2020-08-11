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
        if (email.equals("")){
            map.put("email",null);
        }
        else {
            map.put("email",email);
        }
        if (wechat.equals("")){
            map.put("wechat",null);
        }
        else {
            map.put("wechat",wechat);
        }
        userMapper.addUser(map);
    }
}
