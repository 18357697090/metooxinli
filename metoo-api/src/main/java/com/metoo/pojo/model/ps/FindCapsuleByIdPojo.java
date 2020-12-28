package com.metoo.pojo.model.ps;

import com.metoo.metoo.psychology.Capsule;
import lombok.Data;

@Data
public class FindCapsuleByIdPojo {
    private Capsule capsule;
    private String state;
    private String name;
    private String picture;
}
