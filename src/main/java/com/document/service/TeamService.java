package com.document.service;

import com.document.pojo.Team;

import java.util.List;

public interface TeamService {

    Team getTeamByTeamId(int teamid);
    int addTeam(String teamname,String intro,int leaderid);

    void inviteTeamMember(int userid,int teamid);
    void quitTeam(int userid,int teamid);
}
