package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsCapsuleModel;
import lombok.Data;

@Data
public class FindCapsuleByIdPojo {
    private PsCapsuleModel capsule;
    private String state;
    private String name;
    private String picture;
}
