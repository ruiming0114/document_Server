package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.service.CommentService;
import com.document.service.DocService;
import com.document.service.PermsUtilService;
import com.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.Oneway;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    private DocService docService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PermsUtilService permsUtilService;



    //添加评论
    @PostMapping("/addComment")
    public JsonResult<Map<String,Object>> addComment(@RequestParam("docid")int docid, @RequestParam("content")String content, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    if (permsUtilService.canComment(docid, userid)) {
                        commentService.addComment(docid,userid,content);
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
