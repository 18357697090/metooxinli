package com.metoo.tools;

import com.metoo.pojo.tj.model.TjUserInfoModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatInfo  implements Serializable {
    private Integer seat;
    private TjUserInfoModel userInfo;
}
