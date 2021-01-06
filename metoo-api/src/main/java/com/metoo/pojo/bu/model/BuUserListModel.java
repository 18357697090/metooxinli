package com.metoo.pojo.bu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BuUserListModel {
    private Integer id;

    private String city;

    private String gander;

    private String name;

    private String number;

    private Integer sate;
}
