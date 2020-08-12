package com.document.service;

import com.document.pojo.Doc;

import java.util.Map;

public interface DocService {

    int addDoc(int userid,int teamid);

    Doc readDoc(int docid,int userid);

    void writeDoc(int docid,String title,String content);

    void deleteDoc(int docid);
}
