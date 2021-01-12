package com.metoo.web.config.tools;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class OptionsResult implements Serializable {
    private Integer questionIndex;
    private Integer answerIndex;
}

