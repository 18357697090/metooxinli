package com.metoo.pojo.ps.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PsScaleResultModel implements Serializable {

    private Integer id;

    private Integer scaleId;

    private String conten;

    private String score;
}
