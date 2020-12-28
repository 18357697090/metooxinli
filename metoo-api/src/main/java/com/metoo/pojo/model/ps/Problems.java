package com.metoo.pojo.model.ps;

import com.metoo.metoo.psychology.Options;
import com.metoo.metoo.psychology.Problem;
import lombok.Data;

import java.util.List;

@Data
public class Problems {
    private List<Problem> problems;
    private Options options;
}
