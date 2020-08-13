package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.service.PermsUtilService;
import com.document.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    PermsUtilService permsUtilService;

    @PostMapping("/addTeam")
    public JsonResult<Object> addTeam(@RequestParam("teamname") String teamname, @RequestParam("intro") String intro,@RequestParam("userid") int userid){
        int teamid = teamService.addTeam(teamname,intro,userid);
        return new JsonResult<>(teamService.getTeamByTeamId(teamid),"创建成功");
    }

    @PostMapping("/joinTeam")
    public JsonResult<Object> joinTeam(@RequestParam("teamid") int teamid,@RequestParam("userid") int userid){
        if (permsUtilService.queryTeamPerms(teamid,userid)!=0){
            return new JsonResult<>("1","用户已在团队中");
        }
        else {
            teamService.joinTeam(userid,teamid);
            return new JsonResult<>("0","加入成功");
        }
    }

    @DeleteMapping("/quitTeam")
    public JsonResult<Object> quitTeam(@RequestParam("teamid") int teamid,@RequestParam("userid") int userid){
        if (permsUtilService.queryTeamPerms(teamid,userid)==0){
            return new JsonResult<>("1","未加入团队");
        }
        else if (teamService.getTeamByTeamId(teamid).getLeaderid()==userid){
            return new JsonResult<>("2","创建者不能退出，只能解散团队");
        }
        else {
            teamService.quitTeam(userid,teamid);
            return new JsonResult<>("0","退出成功");
        }
    }
}