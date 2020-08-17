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
    private String intro;
    private String question;
    private String answer;

    public Map<String,Object> getInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("username",username);
        map.put("email",email);
        map.put("wechat",wechat);
        map.put("userimgpath",userimgpath);
        return map;
    }

    public Map<String,Object> getInfo2(){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("username",username);
        map.put("email",email);
        map.put("wechat",wechat);
        map.put("userimgpath",userimgpath);
        map.put("intro",intro);
        map.put("question",question);
        map.put("answer",answer);
        return map;
    }

}
