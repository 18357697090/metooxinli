package com.metoo.tools;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentsTool implements Serializable {
    private int scaleId;
    private String comment;
}
