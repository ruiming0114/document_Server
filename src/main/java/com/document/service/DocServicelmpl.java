package com.document.service;

import com.document.mapper.DocMapper;
import com.document.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DocServicelmpl implements DocService{

    @Autowired
    private DocMapper docMapper;

    @Override
    public void addDoc(Map<String,Object> map) {
        docMapper.addDoc(map);
    }
}
