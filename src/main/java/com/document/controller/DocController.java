package com.document.controller;

import com.document.pojo.Doc;
import com.document.pojo.JsonResult;
import com.document.service.DocService;
import com.document.service.PermsUtilService;
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

    @Autowired
    private PermsUtilService permsUtilService;

    //创建文档
    @PostMapping("/addDoc")
    public JsonResult<Map<String, Object>> addDoc(@RequestParam("title")String title, @RequestParam("content")String content, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    String temp = HtmlUtils.htmlEscapeHex(content);
                    int teamid = -1;
                    docService.addDoc(userid,title,temp,teamid);
                    return new JsonResult<>("0", "保存成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

}
