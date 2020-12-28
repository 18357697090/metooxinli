package com.metoo.pojo.old.model;

import com.metoo.metoo.psychology.Capsule;
import lombok.Data;

@Data
public class FindCapsuleByIdPojo {
    private Capsule capsule;
    private String state;
    private String name;
    private String picture;
}
