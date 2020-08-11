package com.document.mapper;

import com.document.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface DocMapper {

    void addDoc(Map<String,Object> map);
}
