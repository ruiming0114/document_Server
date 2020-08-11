package com.document.service;

import com.document.pojo.Doc;

import java.util.Map;

public interface DocService {

    void addDoc(int userid,String title,String content,int teamid);
}
