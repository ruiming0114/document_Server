package com.document.service;

import com.document.pojo.Doc;

import java.util.Map;

public interface DocService {

    void addDoc(int userid,String title,String content,int teamid);

    Doc readDoc(int docid,int userid);

    void writeDoc(int docid,String title,String content);
}
