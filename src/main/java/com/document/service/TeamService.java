package com.document.service;

import com.document.pojo.Team;

public interface TeamService {

    Team getTeamById(int teamid);
    int addTeam(String teamname,String intro,int leaderid);
}
