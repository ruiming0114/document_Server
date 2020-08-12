package com.document.controller;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.document.pojo.Doc;
import com.document.pojo.JsonResult;
import com.document.service.DocService;
import com.document.service.PermsUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;

@RestController
public class DocController {

    @Autowired
    private DocService docService;

    @Autowired
    private PermsUtilService permsUtilService;

/*    //创建文档
    @PostMapping("/addDoc")
    public JsonResult<Map<String, Object>> addDoc(@RequestParam("title") String title, @RequestParam(value = "content", required = false) String content, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    String temp = HtmlUtils.htmlEscapeHex(content);
                    int teamid = -1;
                    docService.addDoc(userid, title, temp, teamid);
                    return new JsonResult<>("0", "保存成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }*/

    //创建文档
    @PostMapping("/addDoc")
    public JsonResult<Map<String, Object>> addDoc(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    int teamid = -1;
                    int docid = docService.addDoc(userid, teamid);
                    Map<String, Object> map = new HashMap<>();
                    map.put("docid", docid);
                    return new JsonResult<>(map);
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

    //查看文档
    @GetMapping("/readDoc")
    public JsonResult<Map<String, Object>> readDoc(@RequestParam(name="docid") int docid, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    if (permsUtilService.canRead(docid, userid)) {
                        Doc doc = docService.readDoc(docid,userid);
                        Map<String, Object> map = new HashMap<>();
                        map.put("Doc", doc);
                        return new JsonResult<>(map);
                    } else {
                        return new JsonResult<>("2", "没有权限");
                    }
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

    //编辑文档
    @PutMapping("/writeDoc")
    public JsonResult<Map<String,Object>> writeDoc(@RequestParam("docid")int docid,@RequestParam("title") String title, @RequestParam(value = "content", required = false) String content,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    if (permsUtilService.canWrite(docid, userid)) {
                        String temp = HtmlUtils.htmlEscapeHex(content);
                        docService.writeDoc(docid,title,temp);
                        return new JsonResult<>();
                    } else {
                        return new JsonResult<>("2", "没有权限");
                    }
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

    //删除文档
    @DeleteMapping("/deleteDoc")
    public JsonResult<Map<String,Object>> deleteDoc(@RequestParam("docid")int docid,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    if (permsUtilService.canDelete(docid, userid)) {
                        docService.deleteDoc(docid);
                        return new JsonResult<>();
                    } else {
                        return new JsonResult<>("2", "没有权限");
                    }
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

    //恢复文档
    @PutMapping("/recoverDoc")
    public JsonResult<Map<String,Object>> recoverDoc(@RequestParam("docid")int docid,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    if (permsUtilService.canDelete(docid, userid)) {
                        docService.recoverDoc(docid);
                        return new JsonResult<>();
                    } else {
                        return new JsonResult<>("2", "没有权限");
                    }
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }

}
