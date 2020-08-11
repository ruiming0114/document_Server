package com.document.service;

import com.document.mapper.PermsUtilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PermsUtilServicelmpl implements PermsUtilService {
    @Autowired
    private PermsUtilMapper permsUtilMapper;

    @Override
    public void addPerms(Map<String, Object> map) {
        permsUtilMapper.addPerms(map);
    }

    @Override
    public void addPermsOfTeam(Map<String, Object> map) {

    }

    @Override
    public void updatePerms(Map<String, Object> map) {

    }

    @Override
    public void updatePermsOfTeam(Map<String, Object> map) {

    }

    @Override
    public boolean canRead(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean canReadOfTeam(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean canComment(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean canCommentOfTeam(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean canWrite(Map<String, Object> map) {
        return false;
    }

    @Override
    public boolean canWriteOfTeam(Map<String, Object> map) {
        return false;
    }
}
