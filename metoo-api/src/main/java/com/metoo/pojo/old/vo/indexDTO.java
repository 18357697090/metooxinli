package com.metoo.pojo.old.vo;

import com.metoo.metoo.psychology.PourOut;
import com.metoo.metoo.psychology.PsychologyConsult;
import com.metoo.metoo.psychology.Scale;
import lombok.Data;

import java.util.List;

@Data
public class indexDTO {
    private List<Scale> scales;
    private List<PourOut> pourOuts;
    private List<PsychologyConsult> psychologyConsults;
    private List<com.metoo.metoo.DTO.ArticleDTO> articleDTOS;
}
