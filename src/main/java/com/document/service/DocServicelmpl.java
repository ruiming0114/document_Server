package com.document.service;

import com.document.mapper.DocMapper;
import com.document.mapper.PermsUtilMapper;
import com.document.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class DocServicelmpl implements DocService{

    @Autowired
    private DocMapper docMapper;

    @Autowired
    private PermsUtilMapper permsUtilMapper;

    @Override
    public int addDoc(int userid,int teamid) {
        Map<String,Object> map= new HashMap<>();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("createtime",currentTime);
        map.put("userid",userid);
        map.put("title","无标题");
        map.put("content","");
        map.put("shareperms",0);//默认分享权限为0
        map.put("teamid",teamid);
        map.put("modifytime",currentTime);
        map.put("status",0);//默认0为未删除
        map.put("deletetime",null);
        docMapper.addDoc(map);
        map.put("privateperms",3);
        permsUtilMapper.addPerms(map);
        return (int)map.get("docid");
    }

    @Override
    public Doc readDoc(int docid,int userid) {
        Timestamp readtime = new Timestamp(System.currentTimeMillis());
        Map<String,Object> map = new HashMap<>();
        map.put("docid",docid);
        map.put("userid",userid);
        map.put("readtime",readtime);
        docMapper.replaceHistory(map);
        /*if (docMapper.countHistory(map)>10){

            //System.out.println(docMapper.returnFirstHistory(map));
        }*/
        return docMapper.queryDocByDocid(docid);
    }

    @Override
    public void writeDoc(int docid, String title, String content) {
        Map<String,Object> map= new HashMap<>();
        map.put("docid",docid);
        map.put("title",title);
        map.put("content",content);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        map.put("modifytime",currentTime);
        docMapper.writeDoc(map);
    }

    @Override
    public void deleteDoc(int docid) {

    }
}
