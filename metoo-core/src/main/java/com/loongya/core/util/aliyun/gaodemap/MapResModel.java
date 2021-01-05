package com.loongya.core.util.aliyun.gaodemap;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MapResModel implements Serializable {
    private List<Geocodes> geocodes;

}

@Getter
@Setter
class Geocodes implements Serializable{
    private String location;
}



