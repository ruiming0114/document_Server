package com.document.mapper;

import com.document.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    User getUserByUserName(String username);
    User getUserByUserId(int userid);
    void addUser(Map<String,Object> map);
    void updateUser(Map<String,Object> map);
}
