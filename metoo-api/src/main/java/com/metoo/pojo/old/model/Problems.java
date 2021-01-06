package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsScaleOptionsModel;
import com.metoo.pojo.ps.model.PsScaleProblemModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Problems implements Serializable {
    private List<PsScaleProblemModel> problems;
    private PsScaleOptionsModel options;
}
