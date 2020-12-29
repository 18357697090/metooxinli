package com.metoo.pojo.old.vo;

import com.metoo.pojo.xy.model.XyRaceModel;
import lombok.Data;

@Data
public class ReturnRaceDTO {
    private XyRaceModel race;
    private Integer state=0;
    private String userName;
    //1表示进入，2表示聊天室
    private Integer statusBar=1;
}
