package com.document.mapper;


import com.document.pojo.Team;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface TeamMapper {

    Team getTeamByTeamId(int teamid);
    void addTeam(Map<String,Object> map);
}
