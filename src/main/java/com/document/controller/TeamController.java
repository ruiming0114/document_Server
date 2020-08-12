package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/addTeam")
    public JsonResult<Object> addTeam(@RequestParam("teamname") String teamname, @RequestParam("intro") String intro,@RequestParam("userid") int userid){
        int teamid = teamService.addTeam(teamname,intro,userid);
        return new JsonResult<>(teamService.getTeamById(teamid),"创建成功");
    }
}
