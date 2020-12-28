package com.metoo.pojo.model.ps;

import com.metoo.metoo.psychology.PourOut;
import lombok.Data;

import java.util.List;

@Data
public class PourOutPojo {
    private List<PourOutCapsulePojo> pourOutCapsulePojos;
    private List<PourOut> pourOuts;
}
