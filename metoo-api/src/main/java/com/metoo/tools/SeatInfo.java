package com.metoo.tools;

import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import lombok.Data;

@Data
public class SeatInfo {
    private Integer seat;
    private TjUserInfoPojoModel userInfo;
}
