package com.metoo.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppmessageTool implements Serializable {
    private Integer uid;
    private boolean isSuccess;
    private String date;
}
