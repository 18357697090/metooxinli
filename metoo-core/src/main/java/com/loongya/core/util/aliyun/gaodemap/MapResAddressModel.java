package com.loongya.core.util.aliyun.gaodemap;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class MapResAddressModel implements Serializable {
    private String province;

    private String city;

    private String district;
}


