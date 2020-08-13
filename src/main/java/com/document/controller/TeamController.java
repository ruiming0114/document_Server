package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.pojo.User;
import com.document.service.PermsUtilService;
import com.document.service.TeamService;
import com.document.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    PermsUtilService permsUtilService;

    @Autowired
    UserService userService;

    @PostMapping("/addTeam")
    public JsonResult<Object> addTeam(@RequestParam("teamname") String teamname, @RequestParam("intro") String intro,@RequestParam("userid") int userid){
        int teamid = teamService.addTeam(teamname,intro,userid);
        return new JsonResult<>(teamService.getTeamByTeamId(teamid),"创建成功");
    }

    @PostMapping("/inviteTeamMemberByUserId")
    public JsonResult<Object> joinTeam(@RequestParam("teamid") int teamid,@RequestParam("userid") int userid){
        if (userService.getUserByUserId(userid)==null){
            return new JsonResult<>("3","用户不存在");
        }
        if (permsUtilService.queryTeamPerms(teamid,userid)!=0){
            if (permsUtilService.queryTeamPerms(teamid,userid)>0){
                return new JsonResult<>("1","用户已在团队中");
            }
            else {
                return new JsonResult<>("2","已经邀请过该用户，等待同意");
            }
        }
        else {
            teamService.inviteTeamMember(userid,teamid);
            return new JsonResult<>("0","邀请成功，等待对方同意");
        }
    }

    @PostMapping("/inviteTeamMemberByUserName")
    public JsonResult<Object> joinTeam(@RequestParam("teamid") int teamid,@RequestParam("username") String username){
        User user = userService.getUserByUserName(username);
        if (user==null){
            return new JsonResult<>("3","用户不存在");
        }
        int userid = user.getUserid();
        if (permsUtilService.queryTeamPerms(teamid,userid)!=0){
            if (permsUtilService.queryTeamPerms(teamid,userid)>0){
                return new JsonResult<>("1","用户已在团队中");
            }
            else {
                return new JsonResult<>("2","已经邀请过该用户，等待同意");
            }
        }
        else {
            teamService.inviteTeamMember(userid,teamid);
            return new JsonResult<>("0","邀请成功，等待对方同意");
        }
    }

    @PutMapping("/agreeTeamInvitation")
    public JsonResult<Object> agreeTeamInvitation(@RequestParam("teamid") int teamid ,@RequestParam("userid") int userid){
        if (permsUtilService.queryTeamPerms(teamid,userid)!=-1){
            return new JsonResult<>("1","用户未被邀请");
        }
        else {
            permsUtilService.updateTeamPerms(teamid,userid,1);
            return new JsonResult<>("0","同意，加入团队");
        }
    }

    @DeleteMapping("/disagreeTeamInvitation")
    public JsonResult<Object> disagreeTeamInvitation(@RequestParam("teamid") int teamid ,@RequestParam("userid") int userid){
        if (permsUtilService.queryTeamPerms(teamid,userid)!=-1){
            return new JsonResult<>("1","用户未被邀请");
        }
        else {
            permsUtilService.deletePermsOfTeam(teamid,userid);
            return new JsonResult<>("0","拒绝邀请");
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

    @PutMapping("/alterMemberPerms")
    public JsonResult<Object> alterMemberPerms(@RequestParam("teamid") int teamid,@RequestParam("userid") int userid,@RequestParam("teamperms") int teamperms ){
        switch (teamperms){
            case 1:{
                permsUtilService.updateTeamPerms(teamid,userid,1);
                return new JsonResult<>("0","修改成功");
            }
            case 2:{
                permsUtilService.updateTeamPerms(teamid,userid,2);
                return new JsonResult<>("0","修改成功");
            }
            case 3:{
                permsUtilService.updateTeamPerms(teamid,userid,3);
                return new JsonResult<>("0","修改成功");
            }
            default:{
                return new JsonResult<>("1","参数teamperms错误");
            }
        }
    }

    @GetMapping("/getTeamMemberList")
    public JsonResult<Object> getTeamMemberList(@RequestParam("teamid") int teamid){
        Map<String,Object> map = new HashMap<>();
        map.put("memberlist",teamService.getTeamMemberList(teamid));
        return new JsonResult<>(map);
    }

    @GetMapping("/getTeamDocList")
    public JsonResult<Object> getTeamDocList(@RequestParam("teamid") int teamid){
        Map<String,Object> map = new HashMap<>();
        map.put("teamdoclist",teamService.getTeamDocList(teamid));
        return new JsonResult<>(map);
    }
}