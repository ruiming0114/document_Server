package com.document.service;

import com.document.mapper.DocMapper;
import com.document.mapper.PermsUtilMapper;
import com.document.mapper.UserMapper;
import com.document.pojo.Doc;
import com.document.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocServicelmpl implements DocService {

    @Autowired
    private DocMapper docMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermsUtilMapper permsUtilMapper;

    @Override
    public int addDoc(int userid, int teamid) {
        Map<String, Object> map = new HashMap<>();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("createtime", currentTime);
        map.put("userid", userid);
        map.put("title", "无标题");
        map.put("content", "");
        map.put("shareperms", 0);//默认分享权限为0
        map.put("teamid", teamid);
        map.put("modifytime", currentTime);
        map.put("status", 0);//默认0为未删除
        map.put("deletetime", null);
        docMapper.addDoc(map);
        map.put("privateperms", 3);
        permsUtilMapper.addPerms(map);
        return (int) map.get("docid");
    }

    @Override
    public Doc readDoc(int docid, int userid) {
        Timestamp readtime = new Timestamp(System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("userid", userid);
        map.put("readtime", readtime);
        docMapper.replaceHistory(map);
        /*if (docMapper.countHistory(map)>10){

            //System.out.println(docMapper.returnFirstHistory(map));
        }*/
        return docMapper.queryDocByDocid(docid);
    }

    @Override
    public void saveDoc(int docid, String title, String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("title", title);
        map.put("content", content);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("modifytime", currentTime);
        docMapper.saveDoc(map);
    }

    @Override
    public void deleteDoc(int docid) {
        Timestamp deletetime = new Timestamp(System.currentTimeMillis());
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("deletetime", deletetime);
        docMapper.deleteDoc(map);
    }

    @Override
    public void recoverDoc(int docid) {
        docMapper.recoverDoc(docid);
    }

    @Override
    public Map<String, Object> addDocByTemplate(int userid, int teamid, int templateid) {
        Map<String, Object> map = new HashMap<>();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("createtime", currentTime);
        map.put("userid", userid);
        String title = docMapper.getTitleBytemplateid(templateid);
        String content = docMapper.getContentBytemplateid(templateid);
        map.put("title", title);
        map.put("content", content);
        map.put("shareperms", 0);//默认分享权限为0
        map.put("teamid", teamid);
        map.put("modifytime", currentTime);
        map.put("status", 0);//默认0为未删除
        map.put("deletetime", "");
        docMapper.addDoc(map);
        map.put("privateperms", 3);
        permsUtilMapper.addPerms(map);
        return map;
    }

    @Override
    public boolean haveDelete(int docid) {
        return docMapper.getStatus(docid) != 0;
    }

    @Override
    public void updateSharePerms(int docid, int shareperms) {
        docMapper.updateSharePerms(docid, shareperms);
    }

    @Override
    public void collectDoc(int userid, int docid) {
        docMapper.collectDoc(userid, docid);
    }

    @Override
    public void deleteCollection(int userid, int docid) {
        docMapper.deleteCollection(userid, docid);
    }

    @Override
    public boolean haveCollect(int docid, int userid) {
        return docMapper.haveCollect(docid, userid) != null;
    }

    @Override
    public void replacePermsByUserid(int docid, int doneid,int privateperms) {
        Map<String, Object> map = new HashMap<>();
        map.put("docid",docid);
        map.put("userid",doneid);
        map.put("privateperms",privateperms);
        docMapper.replacePermsByUserid(map);
    }

    @Override
    public List<Map<String, Object>> getOnlyCanReadList(int docid) {
        return docMapper.getOnlyCanReadList(docid);
    }

    @Override
    public List<Map<String, Object>> getOnlyCanCommentList(int docid) {
        return docMapper.getOnlyCanCommentList(docid);
    }

    @Override
    public List<Map<String, Object>> getOnlyCanWriteList(int docid) {
        return docMapper.getOnlyCanWriteList(docid);
    }

    @Override
    public void addTemplate(int userid, String title, String content) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("title",title);
        map.put("content",content);
        docMapper.addTemplate(map);
    }

    @Override
    public boolean deleteTemplate(int userid, int templateid) {
        if(userid!=docMapper.getUseridByTemplateid(templateid))
            return false;
        else
            docMapper.deleteTemplate(templateid);
        return true;
    }

    @Override
    public List<Map<String, Object>> getMyTemplateList(int userid) {
        return docMapper.getMyTemplateList(userid);
    }

    @Override
    public Map<String,Object> getTemplateByTemplateid(int templateid) {
        return docMapper.getTemplateByTemplateid(templateid);
    }

    @Override
    public User getUserByDocid(int docid) {
        int userid = docMapper.queryUseridByDocid(docid);
        return userMapper.getUserByUserId(userid);
    }

    @Override
    public void addEditRecord(int docid, int userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("docid",docid);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("modifytime",currentTime);
        docMapper.addEditRecord(map);
    }

    @Override
    public boolean isEditing(int docid) {
        return docMapper.isEditing(docid) != null;
    }

    @Override
    public int getUseridFromEditrecord(int docid) {
        return docMapper.getUseridFromEditrecord(docid);
    }

    @Override
    public void editFinish(int docid, int userid) {
        docMapper.editFinish(docid,userid);
    }
}
