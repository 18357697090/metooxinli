package com.metoo.web.config.tools;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class Result implements Serializable {
    /**
     格式:
     "[{"questionIndex":0,"answerIndex":0},{"questionIndex":0,"answerIndex":0},{"questionIndex":0,"answerIndex":0}]"
     */
    private String resultStr;
    private List<OptionsResult> results;
    private Integer scaleId;
}
