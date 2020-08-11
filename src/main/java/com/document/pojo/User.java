package com.document.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userid;
    private String username;
    private String password;
    private String email;
    private String userimgpath;
    private String wechat;

    public Map<String,Object> getInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("username",username);
        map.put("email",email);
        map.put("wechat",wechat);
        map.put("userimgpath",userimgpath);
        return map;
    }
}
