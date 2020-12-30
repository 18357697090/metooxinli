package com.metoo.user.tj.api;

import com.loongya.core.util.OU;
import com.metoo.api.tj.TjLevelPictureApi;
import com.metoo.pojo.tj.model.TjLevelPictureModel;
import com.metoo.user.tj.dao.entity.TjLevelPicture;
import com.metoo.user.tj.service.TjLevelPictureService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户等级图标表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class TjLevelPictureApiImpl implements TjLevelPictureApi {

    @Autowired
    private TjLevelPictureService tjLevelPictureService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public TjLevelPictureModel findByLevel(Integer dw) {
        TjLevelPicture pojo = tjLevelPictureService.findByLevel(dw);
        if(OU.isBlack(pojo)){
            return null;
        }
        return mapper.map(pojo, TjLevelPictureModel.class);

    }
}
