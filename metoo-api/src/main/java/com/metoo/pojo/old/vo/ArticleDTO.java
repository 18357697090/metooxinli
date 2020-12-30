package com.metoo.pojo.old.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleDTO implements Serializable {
    private Integer articleId;
    private String title;
    private String picture;
    private Integer number;
}
