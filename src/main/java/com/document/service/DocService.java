package com.document.service;

import com.document.pojo.Doc;

import java.util.List;
import java.util.Map;

public interface DocService {

    int addDoc(int userid,int teamid);

    Doc readDoc(int docid,int userid);

    void writeDoc(int docid,String title,String content);

    void deleteDoc(int docid);

    void recoverDoc(int docid);

    Map<String, Object> addDocByTemplate(int userid, int teamid, int templateid);

    boolean haveDelete(int docid);

    void updateSharePerms(int docid, int shareperms);

    void collectDoc(int userid, int docid);

    void deleteCollection(int userid, int docid);

    boolean haveCollect(int docid, int userid);

    void replacePermsByUserid(int docid, int doneid,int privateperms);

    List<Map<String,Object>> getOnlyCanReadList(int docid);

    List<Map<String,Object>> getOnlyCanCommentList(int docid);

    List<Map<String,Object>> getOnlyCanWriteList(int docid);

    void addTemplate(int userid, String title, String content);

    boolean deleteTemplate(int userid, int templateid);

    List<Map<String,Object>> getMyTemplateList(int userid);

    Map<String,Object> getTemplateByTemplateid(int templateid);
}
