package com.document.service;

import com.document.mapper.UserMapper;
import com.document.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addUser(Map<String, Object> map) {
        String email = (String) map.get("email");
        String wechat = (String) map.get("wechat");
        if (email.equals("")){
            map.put("email",null);
        }
        if (wechat.equals("")){
            map.put("wechat",null);
        }
        userMapper.addUser(map);
    }
}
