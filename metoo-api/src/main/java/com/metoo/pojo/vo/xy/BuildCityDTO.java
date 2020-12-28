package com.metoo.pojo.vo.xy;

import lombok.Data;

@Data
public class BuildCityDTO {
    private Integer countryId;
    // 1 为正常的城，2，商家馆  3，倾诉馆 4，请安馆 5，匹配
    private Integer type;
    private String name;
    private String picture;
    private String introduction;
}
