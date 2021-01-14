package com.metoo.ps.ps.api;

import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.ps.PsScaleApi;
import com.metoo.pojo.old.vo.ArticleDTO;
import com.metoo.pojo.old.vo.IndexDTO;
import com.metoo.pojo.ps.model.PsScaleModel;
import com.metoo.pojo.ps.vo.PsScaleVo;
import com.metoo.ps.ps.dao.entity.PsArticle;
import com.metoo.ps.ps.dao.entity.PsScale;
import com.metoo.ps.ps.service.PsArticleService;
import com.metoo.ps.ps.service.PsPourOutService;
import com.metoo.ps.ps.service.PsConsultService;
import com.metoo.ps.ps.service.PsScaleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@DubboService
@Transactional
public class PsScaleApiImpl implements PsScaleApi {

    @Autowired
    private PsScaleService psScaleService;

    @Autowired
    private PsArticleService psArticleService;

    @Autowired
    private PsPourOutService psPourOutService;

    @Autowired
    private PsConsultService psConsultService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE getBoutiqueClgatherList(PsScaleVo vo) {
        return psScaleService.getBoutiqueClgatherList(vo);
    }


    @Override
    public RE findPsScaleListbyclgatherid(PsScaleVo vo) {
        List<PsScale> list = psScaleService.findPsScaleListbyclgatherid(vo.getClgatherId());
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(list.stream().flatMap(e->{
            PsScaleModel model = CopyUtils.copy(e, new PsScaleModel());
            model.setPicture(OSSUtil.fillPath(model.getPicture()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }

    @Override
    public RE indexScaleList() {
        List<PsScaleModel> list = psScaleService.findScaleRand();
        return RE.ok(list);
    }

    @Override
    public RE index() {
        IndexDTO indexDTO = new IndexDTO();
        indexDTO.setScales(psScaleService.findScaleRand());
        List<PsArticle> articles =psArticleService.findArticleRand(6);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (PsArticle article : articles) {
            ArticleDTO articleDTO = mapper.map(article,ArticleDTO.class);
            articleDTOS.add(articleDTO);
        }
        indexDTO.setArticleDTOS(articleDTOS);
        indexDTO.setPourOuts(psPourOutService.findPourOutRand());
        indexDTO.setPsychologyConsults(psConsultService.findPsychologyConsultRand());

        return RE.ok(indexDTO);
    }

}
