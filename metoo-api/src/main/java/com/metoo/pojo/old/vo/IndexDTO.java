package com.metoo.pojo.old.vo;

import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.pojo.ps.model.PsScaleModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IndexDTO implements Serializable {
    private List<PsScaleModel> scales;
    private List<PsPourOutModel> pourOuts;
    private List<PsConsultModel> psychologyConsults;
    private List<ArticleDTO> articleDTOS;
}
