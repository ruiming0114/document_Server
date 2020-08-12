package com.document.service;

import com.document.mapper.DocMapper;
import com.document.mapper.PermsUtilMapper;
import com.document.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PermsUtilServicelmpl implements PermsUtilService {
    @Autowired
    private PermsUtilMapper permsUtilMapper;

    @Autowired
    private DocMapper docMapper;

    @Override
    public int queryPerms(int docid, int userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("userid", userid);
        return permsUtilMapper.queryPerms(map);
    }

    @Override
    public int queryTeamPerms(int teamid, int userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("teamid", teamid);
        map.put("userid", userid);
        return permsUtilMapper.queryTeamPerms(map);
    }

    @Override
    public void addPerms(int docid, int userid, int privateperms) {
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("userid", userid);
        map.put("privateperms",privateperms);
        permsUtilMapper.addPerms(map);
    }

    @Override
    public void addTeamPerms(int teamid, int userid, int teamperms) {
        Map<String, Object> map = new HashMap<>();
        map.put("teamid", teamid);
        map.put("userid", userid);
        map.put("teamperms",teamperms);
        permsUtilMapper.addTeamPerms(map);
    }

    @Override
    public void updatePerms(int docid, int userid, int privateperms) {
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("userid", userid);
        map.put("privateperms",privateperms);
        permsUtilMapper.updatePerms(map);
    }

    @Override
    public void updateTeamPerms(int teamid, int userid, int teamperms) {
        Map<String, Object> map = new HashMap<>();
        map.put("teamid", teamid);
        map.put("userid", userid);
        map.put("teamperms",teamperms);
        permsUtilMapper.updateTeamPerms(map);
    }

    @Override
    public void deletePerms(int docid, int userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("docid", docid);
        map.put("userid", userid);
        permsUtilMapper.deletePerms(map);
    }

    @Override
    public void deletePermsOfTeam(int teamid, int userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("teamid", teamid);
        map.put("userid", userid);
        permsUtilMapper.deleteTeamPerms(map);
    }

    @Override
    public int returnPerms(int docid, int userid) {
        int shareperms, privateperms, teamperms;
        Doc doc = docMapper.queryDocByDocid(docid);
        shareperms = doc.getShareperms();
        if (doc.getTeamid() == -1) {
            teamperms = 0;
        } else {
            teamperms = queryTeamPerms(doc.getTeamid(), userid);
        }
        privateperms = queryPerms(docid, userid);
        return Math.max(Math.max(shareperms, privateperms), teamperms);
    }

    @Override
    public boolean canRead(int docid, int userid) {
        return returnPerms(docid, userid) >= 1;
    }

    @Override
    public boolean canComment(int docid, int userid) {
        return returnPerms(docid, userid) >= 2;
    }

    @Override
    public boolean canWrite(int docid, int userid) {
        return returnPerms(docid, userid) >= 3;
    }

    @Override
    public boolean canDelete(int docid, int userid) {
        int author = docMapper.queryUseridByDocid(docid);
        if(userid==author)
            return true;
        int teamid=docMapper.queryTeamidbyDocid(docid);
        if(teamid==-1)
            return false;
        int leaderid=docMapper.queryLeaderidByTeamid(teamid);
        return leaderid == userid;
    }
}
