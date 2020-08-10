package com.document.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
