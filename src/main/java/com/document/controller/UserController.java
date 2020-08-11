package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUserInfo")
    public JsonResult<Object> getUserInfo(@RequestParam("userid") int userid, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("LoginUserId")){
                    return new JsonResult<>(userService.getUserByUserId(userid).getInfo(),"查询成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

    @PutMapping("/updateUserInfo")
    public JsonResult<Object> updateUserInfo(@RequestParam("userid") int userid,@RequestParam("email") String email,@RequestParam("wechat") String wechat,@RequestParam("userimgpath") String userimgpath,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("LoginUserId")){
                    userService.updateUser(userid,email,wechat,userimgpath);
                    return new JsonResult<>(userService.getUserByUserId(userid).getInfo(),"修改成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }
}
