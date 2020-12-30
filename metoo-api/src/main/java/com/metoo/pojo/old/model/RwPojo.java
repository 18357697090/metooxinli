package com.metoo.pojo.old.model;

import com.metoo.pojo.ta.model.TaTaskModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class RwPojo implements Serializable {
    private TaTaskModel task;
    private String name;
    private String picture;
}
