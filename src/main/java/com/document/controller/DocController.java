package com.document.controller;

import com.document.pojo.Doc;
import com.document.pojo.JsonResult;
import com.document.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;


@RestController
public class DocController {

    @Autowired
    private DocService docService;

    //创建文档
    @PostMapping("/addDoc")
    public JsonResult<Map<String, Object>> addDoc(@RequestParam("title")String title, @RequestParam("content")String content, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    Map<String,Object> map= new HashMap<>();
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    map.put("createtime",currentTime);
                    map.put("userid",Integer.parseInt(cookie.getValue()));
                    map.put("title",title);
                    String temp = HtmlUtils.htmlEscapeHex(content);
                    map.put("content",content);
                    map.put("shareperms",0);//默认分享权限为0
                    map.put("teamid",-1);
                    map.put("modifytime",currentTime);
                    map.put("status",0);//默认0为未删除
                    map.put("deletetime",null);
                    docService.addDoc(map);
                    return new JsonResult<>("0", "保存成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

}
