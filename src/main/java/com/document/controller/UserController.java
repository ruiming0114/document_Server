package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.pojo.User;
import com.document.service.DocService;
import com.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DocService docService;

    @GetMapping("/getUserInfo")
    public JsonResult<Object> getUserInfo(@RequestParam("userid") int userid){
        return new JsonResult<>(userService.getUserByUserId(userid).getInfo(),"查询成功");
    }

    @PutMapping("/updateUserInfo")
    public JsonResult<Object> updateUserInfo(@RequestParam("userid") int userid,@RequestParam("email") String email,@RequestParam("wechat") String wechat){
        userService.updateUserInfo(userid,email,wechat);
        return new JsonResult<>(userService.getUserByUserId(userid).getInfo(),"修改成功");
    }

    @PutMapping("/alterPassword")
    public JsonResult<Object> alterPassword(@RequestParam("userid") int userid,@RequestParam("oldpwd") String oldpwd,@RequestParam("newpwd") String newpwd){
        User user = userService.getUserByUserId(userid);
        if (user.getPassword().equals(oldpwd)){
            userService.updateUserPwd(userid,newpwd);
            return new JsonResult<>("0","修改成功");
        }
        else {
            return new JsonResult<>("1","密码错误");
        }
    }

    @GetMapping("/getRecentRead")
    public JsonResult<Object> getRecentRead(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("readlist",userService.getRecentReadDoc(userid));
        return new JsonResult<>(map,"操作成功");
    }

    @GetMapping("/getCollected")
    public JsonResult<Object> getCollected(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("collectlist",userService.getCollectedDoc(userid));
        return new JsonResult<>(map,"操作成功");
    }

    @GetMapping("/getMyDoc")
    public JsonResult<Object> getMyDoc(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("mydoclist",userService.getMyDoc(userid));
        return new JsonResult<>(map,"操作成功");
    }

    @GetMapping("/getMyDeleteDoc")
    public JsonResult<Object> getMyDeleteDoc(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("mydeletelist",userService.getMyDeleteDoc(userid));
        return new JsonResult<>(map,"操作成功");
    }


    @GetMapping("/getMyCreatedTeam")
    public JsonResult<Object> getCreatedTeamList(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("createlist",userService.getCreatedTeam(userid));
        return new JsonResult<>(map);
    }

    @GetMapping("/getMyJoinedTeam")
    public JsonResult<Object> getJoinedTeamList(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("joinlist",userService.getJoinedTeam(userid));
        return new JsonResult<>(map);
    }
}
