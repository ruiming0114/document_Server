package com.document.service;

import com.document.mapper.DocMapper;
import com.document.mapper.TeamMapper;
import com.document.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    @Autowired
    PermsUtilService permsUtilService;

    @Autowired
    DocMapper docMapper;

    @Override
    public Team getTeamByTeamId(int teamid) {
        return teamMapper.getTeamByTeamId(teamid);
    }

    @Override
    public int addTeam(String teamname, String intro, int leaderid) {
        Map<String,Object> map = new HashMap<>();
        map.put("teamname",teamname);
        map.put("intro",intro);
        map.put("leaderid",leaderid);
        teamMapper.addTeam(map);
        permsUtilService.addTeamPerms((Integer) map.get("teamid"),leaderid,3);
        return (int) map.get("teamid");
    }

    @Override
    public void inviteTeamMember(int userid, int teamid) {
        permsUtilService.addTeamPerms(teamid,userid,-1);
        Team team = teamMapper.getTeamByTeamId(teamid);
        noticeService.addTeamInvitationNotice(userid,team.getTeamname(),userService.getUserByUserId(team.getLeaderid()).getUsername(),teamid);
    }

    @Override
    public void quitTeam(int userid, int teamid) {
        permsUtilService.deletePermsOfTeam(teamid,userid);
    }

    @Override
    public List<Map<String, Object>> getTeamMemberList(int teamid) {
        return teamMapper.getTeamMemberList(teamid);
    }

    @Override
    public List<Map<String, Object>> getTeamDocList(int teamid) {
        return teamMapper.getTeamDocList(teamid);
    }

    @Override
    public void deleteTeam(int teamid) {
        List<Map<String,Object>> memberList = teamMapper.getTeamMemberList(teamid);
        for (Map<String,Object> member : memberList){
            permsUtilService.deletePermsOfTeam(teamid, (Integer) member.get("userid"));
            noticeService.addMemberNotice_delete((Integer)member.get("userid"),teamMapper.getTeamByTeamId(teamid).getTeamname());
        }
        List<Map<String,Object>> docList = teamMapper.getTeamDocList(teamid);
        for (Map<String,Object> doc : docList){
            docMapper.deleteDocTotally((Integer) doc.get("docid"));
        }
        noticeService.deleteNoticeByTeam(teamid);
        teamMapper.deleteTeam(teamid);
    }
}
