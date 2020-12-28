package com.metoo.pojo.old.vo;

import com.metoo.metoo.entity.Race;
import lombok.Data;

@Data
public class ReturnRaceDTO {
    private Race race;
    private Integer state=0;
    private String userName;
    //1表示进入，2表示聊天室
    private Integer statusBar=1;
}
