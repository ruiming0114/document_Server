package com.document.mapper;

import com.document.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
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

    void writeDoc(Map<String,Object> map);
}
