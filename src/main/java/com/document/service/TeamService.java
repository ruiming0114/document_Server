package com.document.service;

import com.document.pojo.Team;

import java.util.List;
import java.util.Map;

public interface TeamService {

    Team getTeamByTeamId(int teamid);
    int addTeam(String teamname,String intro,int leaderid);

    void inviteTeamMember(int userid,int teamid);
    void quitTeam(int userid,int teamid);
    void updateTeam(int teamid,String teamname,String intro);

    List<Map<String,Object>> getTeamMemberList(int teamid);
    List<Map<String,Object>> getTeamDocList(int teamid);

    void deleteTeam(int teamid);
}
