package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnRaceDTO implements Serializable {
    private Integer state=0;
    private String userName;
    //1表示进入，2表示聊天室
    private Integer statusBar=1;
}
