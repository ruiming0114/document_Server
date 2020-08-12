package com.document.service;

import com.document.mapper.CommentMapper;
import com.document.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServicelmpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void addComment(int docid, int userid, String content) {
        Timestamp commenttime = new Timestamp(System.currentTimeMillis());
        Map<String,Object> map = new HashMap<>();
        map.put("docid",docid);
        map.put("userid",userid);
        map.put("content",content);
        map.put("commenttime",commenttime);
        commentMapper.addComment(map);
    }

    @Override
    public List<Map<String, Object>> getCommentList(int docid) {
        return commentMapper.getCommentList(docid);
    }
}
