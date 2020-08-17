package com.document.mapper;

import com.document.pojo.Doc;
import com.document.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DocMapper {

    void addDoc(Map<String,Object> map);

    Doc queryDocByDocid(int docid);

    void addHistory(Map<String,Object> map);

    void replaceHistory(Map<String,Object> map);

    int countHistory(Map<String,Object> map);

    void deleteFirstHistory(Map<String,Object> map);

    Timestamp returnFirstHistory(Map<String,Object> map);

    void saveDoc(Map<String,Object> map);

    int queryUseridByDocid(int docid);

    int queryLeaderidByTeamid(int teamid);

    int queryTeamidbyDocid(int docid);

    void deleteDoc(Map<String,Object> map);

    void deleteDocTotally(int docid);

    void recoverDoc(int docid);

    String getTitleBytemplateid(int templateid);

    String getContentBytemplateid(int templateid);

    int getStatus(int docid);

    void updateSharePerms(int docid, int shareperms);

    void collectDoc(int userid, int docid);

    void deleteCollection(int userid, int docid);

    Integer haveCollect(int docid, int userid);

    void replacePermsByUserid(Map<String, Object> map);

    List<Map<String, Object>> getOnlyCanReadList(int docid);

    List<Map<String, Object>> getOnlyCanCommentList(int docid);

    List<Map<String, Object>> getOnlyCanWriteList(int docid);

    void addTemplate(Map<String, Object> map);

    int getUseridByTemplateid(int templateid);

    void deleteTemplate(int templateid);

    List<Map<String, Object>> getMyTemplateList(int userid);
    List<Map<String, Object>> getTeamTemplateList(int teamid);

    Map<String, Object> getTemplateByTemplateid(int templateid);

    void addEditRecord(Map<String, Object> map);

    Integer isEditing(int docid);

    int getUseridFromEditrecord(int docid);

    void editFinish(int docid, int userid);

    List<Map<String, Object>> getModifyList(int docid);

    List<Map<String, Object>> getUserListByUsername(String username);

    List<Map<String, Object>> getRelatedDocByTitle(Map<String, Object> map);
}
