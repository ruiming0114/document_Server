package com.document.controller;

import com.document.pojo.Doc;
import com.document.pojo.JsonResult;
import com.document.pojo.User;
import com.document.service.DocService;
import com.document.service.PermsUtilService;
import com.document.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DocController {

    @Autowired
    private DocService docService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermsUtilService permsUtilService;

    //创建文档
    @PostMapping("/addDoc")
    public JsonResult<Map<String, Object>> addDoc(@RequestParam("userid") int userid) {
        int teamid = -1;
        int docid = docService.addDoc(userid, teamid);
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        return new JsonResult<>(map);
    }

    //查看文档
    @GetMapping("/readDoc")
    public JsonResult<Map<String, Object>> readDoc(@RequestParam("userid") int userid, @RequestParam(name = "docid") int docid) {
        if (docService.haveDelete(docid))
            return new JsonResult<>("2", "文档已被删除！");
        if (permsUtilService.canRead(docid, userid)) {
            Doc doc = docService.readDoc(docid, userid);
            boolean haveCollect = docService.haveCollect(docid, userid);
            String returnHtml = HtmlUtils.htmlUnescape(doc.getContent());
            doc.setContent(returnHtml);
            User author = userService.getUserByUserId(doc.getUserid());
            Map<String, Object> map = new HashMap<>();
            map.put("doc", doc);
            map.put("authorname", author.getUsername());
            map.put("authorimgpath", author.getUserimgpath());
            map.put("canComment", permsUtilService.canComment(docid, userid));
            map.put("haveCollect", haveCollect);
            return new JsonResult<>(map);
        } else {
            return new JsonResult<>("1", "没有权限");
        }
    }

    //编辑文档
    @PutMapping("/writeDoc")
    public JsonResult<Map<String, Object>> writeDoc(@RequestParam("userid") int userid, @RequestParam("docid") int docid, @RequestParam("title") String title, @RequestParam(value = "content", required = false) String content) {
        if (permsUtilService.canWrite(docid, userid)) {
            String temp = HtmlUtils.htmlEscapeHex(content);
            docService.writeDoc(docid, title, temp);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }
    }

    //删除文档
    @DeleteMapping("/deleteDoc")
    public JsonResult<Map<String, Object>> deleteDoc(@RequestParam("userid") int userid, @RequestParam("docid") int docid) {

        if (permsUtilService.canDelete(docid, userid)) {
            docService.deleteDoc(docid);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }

    }

    //恢复文档
    @PutMapping("/recoverDoc")
    public JsonResult<Map<String, Object>> recoverDoc(@RequestParam("userid") int userid, @RequestParam("docid") int docid) {

        if (permsUtilService.canDelete(docid, userid)) {
            docService.recoverDoc(docid);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }

    }

    //基于模板创建
    @PostMapping("/addDocByTemplate")
    public JsonResult<Map<String, Object>> addDocByTemplate(@RequestParam("userid") int userid, @RequestParam("templateid") int templateid) {
        int teamid = -1;
        Map<String, Object> map = docService.addDocByTemplate(userid, teamid, templateid);
        String returnHtml = HtmlUtils.htmlUnescape((String) map.get("content"));
        map.put("content", returnHtml);
        return new JsonResult<>(map);
    }

    //修改文章分享权限
    @PutMapping("/updateSharePerms")
    public JsonResult<Map<String, Object>> updateSharePerms(@RequestParam("userid") int userid, @RequestParam("docid") int docid, @RequestParam("shareperms") int shareperms) {
        if (permsUtilService.canShare(docid, userid)) {
            docService.updateSharePerms(docid, shareperms);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }
    }

    //创建团队文档
    @PostMapping("/addTeamDoc")
    public JsonResult<Map<String, Object>> addTeamDoc(@RequestParam("userid") int userid, @RequestParam("teamid") int teamid) {
        int docid = docService.addDoc(userid, teamid);
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        return new JsonResult<>(map);
    }

    //基于模板创建
    @PostMapping("/addTeamDocByTemplate")
    public JsonResult<Map<String, Object>> addTeamDocByTemplate(@RequestParam("userid") int userid, @RequestParam("templateid") int templateid, @RequestParam("teamid") int teamid) {
        Map<String, Object> map = docService.addDocByTemplate(userid, teamid, templateid);
        String returnHtml = HtmlUtils.htmlUnescape((String) map.get("content"));
        map.put("content", returnHtml);
        return new JsonResult<>(map);
    }

    //收藏文档
    @PutMapping("/collectDoc")
    public JsonResult<Map<String, Object>> collectDoc(@RequestParam("userid") int userid, @RequestParam("docid") int docid) {
        docService.collectDoc(userid, docid);
        return new JsonResult<>();
    }

    //取消收藏
    @DeleteMapping("/deleteCollection")
    public JsonResult<Map<String, Object>> deleteCollection(@RequestParam("userid") int userid, @RequestParam("docid") int docid) {
        docService.deleteCollection(userid, docid);
        return new JsonResult<>();
    }

    //通过userid设置权限
    @PutMapping("/replacePermsByUserid")
    public JsonResult<Map<String, Object>> replacePermsByUserid(@RequestParam("doid") int doid, @RequestParam("doneid") int doneid, @RequestParam("privateperms") int privateperms, @RequestParam("docid") int docid) {
        if (permsUtilService.canDelete(docid, doid)) {
            docService.replacePermsByUserid(docid, doneid, privateperms);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }
    }

    //移除privateperms权限
    @DeleteMapping("/deletePerms")
    public JsonResult<Map<String, Object>> deletePerms(@RequestParam("doid") int doid, @RequestParam("doneid") int doneid, @RequestParam("docid") int docid) {
        if (permsUtilService.canDelete(docid, doid)) {
            permsUtilService.deletePerms(docid, doneid);
            return new JsonResult<>();
        } else {
            return new JsonResult<>("1", "没有权限");
        }
    }

    //权限用户列表
    @GetMapping("/getPermsList")
    public JsonResult<Map<String, Object>> getPermsList(@RequestParam("docid") int docid) {
        Map<String, Object> map = new HashMap<>();
        map.put("onlyCanReadList", docService.getOnlyCanReadList(docid));
        map.put("onlyCanCommentList", docService.getOnlyCanCommentList(docid));
        map.put("onlyCanWriteList", docService.getOnlyCanWriteList(docid));
        return new JsonResult<>(map);
    }

    //根据username查找用户
    @GetMapping("/getUserByUsername")
    public JsonResult<Map<String, Object>> getUserByUsername(@RequestParam("username") String username) {
        User user = userService.getUserByUserName(username);
        if(user==null)
            return new JsonResult<>("1","没有符合条件的用户");
        Map<String, Object> map = new HashMap<>();
        map.put("user",user.getInfo());
        return new JsonResult<>(map);
    }
}