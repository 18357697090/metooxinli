package com.metoo.pojo.old.model;

import com.metoo.pojo.ps.model.PsOptionsModel;
import com.metoo.pojo.ps.model.PsProblemModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Problems implements Serializable {
    private List<PsProblemModel> problems;
    private PsOptionsModel options;
}
