package com.loongya.core.util.aliyun;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OSSModel implements Serializable {

    private String fullpath;
    private String subpath;

}
