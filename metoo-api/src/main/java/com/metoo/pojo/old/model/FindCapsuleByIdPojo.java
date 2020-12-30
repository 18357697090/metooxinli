package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsCapsuleModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class FindCapsuleByIdPojo implements Serializable {
    private PsCapsuleModel capsule;
    private String state;
    private String name;
    private String picture;
}
