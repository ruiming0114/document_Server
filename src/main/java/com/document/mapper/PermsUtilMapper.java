package com.document.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface PermsUtilMapper {

    //查询个人权限
    int queryPerms(Map<String,Object> map);
    //查询队内权限
    int queryTeamPerms(Map<String,Object> map);
    //添加个人权限
    void addPerms(Map<String,Object> map);
    //添加队内权限
    void addTeamPerms(Map<String,Object> map);
    //修改个人权限
    void updatePerms(Map<String,Object> map);
    //修改队内权限
    void updateTeamPerms(Map<String,Object> map);
    //删除个人权限
    void deletePerms(Map<String,Object> map);
    //删除队内权限
    void deleteTeamPerms(Map<String,Object> map);

}
