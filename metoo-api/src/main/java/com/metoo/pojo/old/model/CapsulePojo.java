package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsCapsuleModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class CapsulePojo implements Serializable {
    private PsCapsuleModel psCapsuleModel;
    private String name;
    private String picture;

}
