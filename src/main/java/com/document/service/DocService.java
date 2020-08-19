package com.document.service;

import com.document.pojo.Doc;
import com.document.pojo.User;

import java.util.List;
import java.util.Map;

public interface DocService {

    int addDoc(int userid,int teamid);
    Map<String, Object> addDocByTemplate(int userid, int teamid, int templateid);
    Doc readDoc(int docid,int userid);
    void saveDoc(int docid,String title,String content);

    void deleteDoc(int docid);
    void deleteDocTotally(int docid);
    void recoverDoc(int docid);
    boolean haveDelete(int docid);

    void updateSharePerms(int docid, int shareperms);

    void collectDoc(int userid, int docid);
    void deleteCollection(int userid, int docid);
    boolean haveCollect(int docid, int userid);

    void replacePermsByUserid(int docid, int doneid,int privateperms);

    List<Map<String,Object>> getOnlyCanReadList(int docid);
    List<Map<String,Object>> getOnlyCanCommentList(int docid);
    List<Map<String,Object>> getOnlyCanWriteList(int docid);

    void addTemplate(int userid, String title, String content,int teamid);
    boolean deleteTemplate(int userid, int templateid);

    List<Map<String,Object>> getMyTemplateList(int userid);
    List<Map<String,Object>> getTeamTemplateList(int teamid);

    Map<String,Object> getTemplateByTemplateid(int templateid);

    User getUserByDocid(int docid);

    void addEditRecord(int docid, int userid);
    boolean isEditing(int docid);
    int getUseridFromEditrecord(int docid);
    void editFinish(int docid, int userid);
    List<Map<String,Object>> getModifyList(int docid);

    List<Map<String,Object>> getUserByUsername(String username);

    List<Map<String,Object>> getRelatedDocByTitle(int userid, String search);

    String getTitleByDocid(int docid);

    int getReadNum(int docid);
}
