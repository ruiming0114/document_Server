package com.document.mapper;

import com.document.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DocMapper {

    void addDoc(Doc doc);
}
