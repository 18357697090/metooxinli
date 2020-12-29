package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsPourOutModel;
import lombok.Data;

import java.util.List;

@Data
public class PourOutPojo {
    private List<PourOutCapsulePojo> pourOutCapsulePojos;
    private List<PsPourOutModel> pourOuts;
}
