package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsPourOutModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PourOutPojo  implements Serializable {
    private List<PourOutCapsulePojo> pourOutCapsulePojos;
    private List<PsPourOutModel> pourOuts;
}
