package com.document.controller;

import com.document.pojo.JsonResult;
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
    public JsonResult<Object> updateUserInfo(@RequestParam("userid") int userid,@RequestParam("email") String email,@RequestParam("wechat") String wechat,@RequestParam("userimgpath") String userimgpath){
        userService.updateUser(userid,email,wechat,userimgpath);
        return new JsonResult<>(userService.getUserByUserId(userid).getInfo(),"修改成功");
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

}
