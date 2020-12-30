package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.pojo.old.vo.ArticleDTO;
import com.metoo.pojo.old.vo.IndexDTO;
import com.metoo.pojo.ps.model.PsScaleModel;
import com.metoo.ps.ps.dao.entity.PsArticle;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.service.PsArticleService;
import com.metoo.ps.ps.service.PsPourOutService;
import com.metoo.ps.ps.service.PsPsychologyConsultService;
import com.metoo.ps.ps.service.PsScaleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@DubboService
public class PsScaleApiImpl implements PsScaleApi {

    @Autowired
    private PsScaleService psScaleService;

    @Autowired
    private PsArticleService psArticleService;

    @Autowired
    private PsPourOutService psPourOutService;

    @Autowired
    private PsPsychologyConsultService psPsychologyConsultService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE cl(Integer page) {
        return psScaleService.cl(page);
    }

    @Override
    public RE index() {
        IndexDTO indexDTO = new IndexDTO();
        indexDTO.setScales(psScaleService.findScaleRand());
        List<PsArticle> articles =psArticleService.findArticleRand();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (PsArticle article : articles) {
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            articleDTOS.add(articleDTO);
        }
        indexDTO.setArticleDTOS(articleDTOS);
        indexDTO.setPourOuts(psPourOutService.findPourOutRand());
        indexDTO.setPsychologyConsults(psPsychologyConsultService.findPsychologyConsultRand());

        return RE.ok(indexDTO);
    }

    @Override
    public RE findbyclgatherid(Integer clgatherid) {
        List<PsScale> list = psScaleService.findByScaleGatherId(clgatherid);
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(list.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsScaleModel.class));
        }).collect(Collectors.toList()));
    }
}
