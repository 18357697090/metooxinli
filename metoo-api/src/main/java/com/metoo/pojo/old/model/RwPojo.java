package com.metoo.pojo.old.model;

import com.metoo.pojo.ta.model.TaTaskModel;
import lombok.Data;

@Data
public class RwPojo {
    private TaTaskModel task;
    private String name;
    private String picture;
}
