package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.pojo.User;
import com.document.service.NoticeService;
import com.document.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    NoticeService noticeService;

    @PostMapping("/login")
    public JsonResult<Object> login(@RequestParam("username") String username, @RequestParam("password") String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try {
            subject.login(usernamePasswordToken);
            User user = userService.getUserByUserName(username);
            return new JsonResult<>(user.getInfo2(),"登陆成功");
        }catch (UnknownAccountException e){
            return new JsonResult<>("1","用户名不存在");
        }catch (IncorrectCredentialsException e){
            return new JsonResult<>("2","密码错误");
        }
    }

    @PostMapping("/register")
    public JsonResult<Object> register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email,@RequestParam("wechat") String wechat,@RequestParam("intro") String intro,@RequestParam("question") String question,@RequestParam("answer") String answer){
        User user = userService.getUserByUserName(username);
        if (user!=null){
            return new JsonResult<>("1","用户名重复");
        }
        userService.addUser(username,password,email,wechat,intro,question,answer);
        noticeService.addWelcomeNotice(userService.getUserByUserName(username).getUserid());
        return new JsonResult<>("0","注册成功");
    }
}
