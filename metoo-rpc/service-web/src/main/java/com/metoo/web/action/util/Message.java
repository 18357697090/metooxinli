package com.metoo.web.action.util;

import lombok.Data;

@Data
public class Message {
    private String message;
    private String sendId;
    private String sendName;
    private String sendImg;
    // img word
    private String type;
}
