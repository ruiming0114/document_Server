package com.document.controller;

import com.document.pojo.Doc;
import com.document.pojo.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.sql.Timestamp;

public class DocController {

    //创建文档
    @PostMapping("/addDoc")
    public JsonResult<Map<String, Object>> addDoc(@RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("teamid")int teamid, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    Doc doc = new Doc();
                    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                    doc.setCreatetime(currentTime);
                    doc.setUserid(Integer.parseInt(cookie.getValue()));
                    doc.setTitle(title);
                    String temp = HtmlUtils.htmlEscapeHex(content);
                    doc.setContent(temp);
                    doc.setShareperms(0);//默认为0
                    doc.setTeamid(teamid);
                    doc.setModifytime(currentTime);
                    doc.setStatus(0);//默认为0
                    return new JsonResult<>("0", "保存成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

}
