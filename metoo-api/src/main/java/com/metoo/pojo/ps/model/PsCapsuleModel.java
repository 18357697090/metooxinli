package com.metoo.pojo.ps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsCapsuleModel implements Serializable {
    private Long id;

    private Integer attribute;

    private Integer beWatched;

    private Integer capsuleId;

    private String content;

    private Date creationTime;

    private String picture1;

    private String picture2;

    private String picture3;

    private String picture4;

    private String picture5;

    private String picture6;

    private String picture7;

    private String picture8;

    private String picture9;

    private Integer prices;

    private String title;

    private Integer type;

    private Integer uid;

    private Integer state;
}
