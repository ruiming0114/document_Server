package com.document.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private int teamid;
    private String teamname;
    private String intro;
    private int leaderid;
}
