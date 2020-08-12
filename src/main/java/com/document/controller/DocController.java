package com.document.controller;

import com.document.pojo.Doc;
import com.document.pojo.JsonResult;
import com.document.pojo.User;
import com.document.service.DocService;
import com.document.service.PermsUtilService;
import com.document.service.UserService;
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
        if (permsUtilService.canRead(docid, userid)) {
            Doc doc = docService.readDoc(docid, userid);
            String returnHtml = HtmlUtils.htmlUnescape(doc.getContent());
            doc.setContent(returnHtml);
            User author = userService.getUserByUserId(doc.getUserid());
            Map<String, Object> map = new HashMap<>();
            map.put("doc", doc);
            map.put("authorname", author.getUsername());
            map.put("authorimgpath", author.getUserimgpath());
            map.put("canComment", permsUtilService.canComment(docid, userid));
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
        Map<String,Object> map = docService.addDocByTemplate(userid, teamid, templateid);
        String returnHtml = HtmlUtils.htmlUnescape((String) map.get("content"));
        map.put("content", returnHtml);
        return new JsonResult<>(map);
    }
}
