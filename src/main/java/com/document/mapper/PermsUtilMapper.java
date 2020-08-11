package com.document.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface PermsUtilMapper {

    int queryPerms(Map<String,Object> map);

    int queryPermsOfTeam(Map<String,Object> map);

    void addPerms(Map<String,Object> map);

    void addPermsOfTeam(Map<String,Object> map);

    void updatePerms(Map<String,Object> map);

    void updatePermsOfTeam(Map<String,Object> map);

    void deletePerms(Map<String,Object> map);

    void deletePermsOfTeam(Map<String,Object> map);

}
