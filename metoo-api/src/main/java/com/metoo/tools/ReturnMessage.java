package com.metoo.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnMessage implements Serializable {
    private String state;
    private String explain;
    private Object object;
    private String spare;
}
