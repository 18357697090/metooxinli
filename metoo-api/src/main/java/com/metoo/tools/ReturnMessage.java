package com.metoo.tools;

import lombok.Data;

@Data
public class ReturnMessage {
    private String state;
    private String explain;
    private Object object;
    private String spare;
}
