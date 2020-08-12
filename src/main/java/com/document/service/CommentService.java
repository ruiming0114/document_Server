package com.document.service;

import java.util.List;
import java.util.Map;

public interface CommentService {

    void addComment(int docid, int userid, String content);

    List<Map<String,Object>> getCommentList(int docid);
}
