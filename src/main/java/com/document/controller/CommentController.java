package com.document.controller;

import com.document.mapper.DocMapper;
import com.document.pojo.JsonResult;
import com.document.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CommentController {

    @Autowired
    DocMapper docMapper;

    @Autowired
    private DocService docService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    private PermsUtilService permsUtilService;

    //添加评论
    @PostMapping("/addComment")
    public JsonResult<Map<String, Object>> addComment(@RequestParam("userid") int userid, @RequestParam("docid") int docid, @RequestParam("content") String content) {

        if (permsUtilService.canComment(docid, userid)) {
            commentService.addComment(docid, userid, content);
            noticeService.addCommentNotice(userid,docid,userService.getUserByUserId(userid).getUsername(),docMapper.queryDocByDocid(docid).getTitle());
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }

    }

    //查询评论列表
    @GetMapping("/getCommentList")
    public JsonResult<Map<String, Object>> getCommentList(@RequestParam("docid") int docid) {

        Map<String, Object> map = new HashMap<>();
        map.put("commentList", commentService.getCommentList(docid));
        return new JsonResult<>(map);

    }

    //删除评论
    @DeleteMapping("/deleteComment")
    public JsonResult<Map<String, Object>> deleteComment(@RequestParam("userid")int userid,@RequestParam("commentid")int commentid) {
        if(commentService.isHisComment(userid,commentid)){
            commentService.deleteComment(commentid);
            return new JsonResult<>();
        }else{
            return new JsonResult<>("1", "没有权限");
        }
    }
}
