package com.document.service;

import com.document.mapper.UserMapper;
import com.document.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    public void updateUserInfo(int userid,String email, String wechat) {
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("email",email);
        map.put("wechat",wechat);
        User user = userMapper.getUserByUserId(userid);
        map.put("password",user.getPassword());
        map.put("userimgpath",user.getUserimgpath());
        userMapper.updateUser(map);
    }

    @Override
    public void updateUserImage(int userid, String userimgpath) {
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("userimgpath",userimgpath);
        User user = userMapper.getUserByUserId(userid);
        map.put("email",user.getEmail());
        map.put("wechat",user.getWechat());
        map.put("password",user.getUserimgpath());
        userMapper.updateUser(map);
    }

    @Override
    public void updateUserPwd(int userid, String password) {
        Map<String ,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("password",password);
        User user = userMapper.getUserByUserId(userid);
        map.put("email",user.getEmail());
        map.put("userimgpath",user.getUserimgpath());
        map.put("wechat",user.getWechat());
        userMapper.updateUser(map);
    }

    @Override
    public List<Map<String, Object>> getRecentReadDoc(int userid) {
        return userMapper.getRecentReadDoc(userid);
    }

    @Override
    public List<Map<String, Object>> getCollectedDoc(int userid) {
        return userMapper.getCollectedDoc(userid);
    }

    @Override
    public List<Map<String, Object>> getMyDoc(int userid) {
        return userMapper.getMyDoc(userid);
    }

    @Override
    public List<Map<String, Object>> getMyDeleteDoc(int userid) {
        return userMapper.getMyDeleteDoc(userid);
    }
}
