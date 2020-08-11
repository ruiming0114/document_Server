package com.document.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface PermsUtilMapper {
    void addPerms(Map<String,Object> map);

    void addPermsOfTeam(Map<String,Object> map);

}
