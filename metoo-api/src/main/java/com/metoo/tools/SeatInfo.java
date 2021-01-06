package com.metoo.tools;


import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeatInfo  implements Serializable {
    private Integer seat;
    private TjUserInfoPojoModel userInfo;
}
