package com.metoo.pojo.old.vo;

import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.pojo.ps.model.PsPsychologyConsultModel;
import com.metoo.pojo.ps.model.PsScaleModel;
import lombok.Data;

import java.util.List;

@Data
public class indexDTO {
    private List<PsScaleModel> scales;
    private List<PsPourOutModel> pourOuts;
    private List<PsPsychologyConsultModel> psychologyConsults;
    private List<ArticleDTO> articleDTOS;
}
