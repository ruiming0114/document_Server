package com.document.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface CommentMapper {
    void addComment(Map<String, Object> map);
}
