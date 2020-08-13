package com.document.service;

import com.document.mapper.TeamMapper;
import com.document.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    PermsUtilService permsUtilService;

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
    public void joinTeam(int userid, int teamid) {
        permsUtilService.addTeamPerms(teamid,userid,1);
    }

    @Override
    public void quitTeam(int userid, int teamid) {
        permsUtilService.deletePermsOfTeam(teamid,userid);
    }
}
