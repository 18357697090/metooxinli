package com.metoo.ps.ps.api;

import com.loongya.core.util.CommsEnum;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsArticleBannerApi;
import com.metoo.pojo.old.vo.ArticleDTO;
import com.metoo.pojo.old.vo.ArticleIndexDTO;
import com.metoo.pojo.ps.model.PsArticleBannerModel;
import com.metoo.ps.ps.dao.entity.PsArticle;
import com.metoo.ps.ps.service.PsArticleBannerService;
import com.metoo.ps.ps.service.PsArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 心理文章轮播图表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsArticleBannerApiImpl implements PsArticleBannerApi {

    @Autowired
    private PsArticleBannerService psArticleBannerService;

    @Override
    public RE getBannerList() {
        List<PsArticleBannerModel> list = psArticleBannerService.findAll();
        if(OU.isNotBlack(list)){
            return RE.ok(list);
        }
        return RE.fail(CommsEnum.NO_DATA);
    }
}
