package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/addTeam")
    public JsonResult<Object> addTeam(@RequestParam("teamname") String teamname, @RequestParam("intro") String intro, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("LoginUserId")) {
                    int userid = Integer.parseInt(cookie.getValue());
                    int teamid = teamService.addTeam(teamname,intro,userid);
                    return new JsonResult<>(teamService.getTeamById(teamid),"创建成功");
                }
            }
        }
        return new JsonResult<>("1", "用户未登录");
    }
}
