package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.service.CommentService;
import com.document.service.DocService;
import com.document.service.PermsUtilService;
import com.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public JsonResult<Map<String, Object>> addComment(@RequestParam("userid") int userid, @RequestParam("docid") int docid, @RequestParam("content") String content) {

        if (permsUtilService.canComment(docid, userid)) {
            commentService.addComment(docid, userid, content);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }

    }

    //查询评论列表
    @GetMapping("/getCommentList")
    public JsonResult<Map<String, Object>> getCommentList(@RequestParam("userid") int userid, @RequestParam("docid") int docid) {

        Map<String, Object> map = new HashMap<>();
        map.put("commentList", commentService.getCommentList(docid));
        return new JsonResult<>(map);

    }
}
