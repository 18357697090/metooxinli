package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.metoo.api.ps.PsCapsuleApi;
import com.metoo.pojo.ps.model.PsCapsuleModel;
import com.metoo.ps.ps.dao.entity.PsCapsule;
import com.metoo.ps.ps.service.PsCapsuleService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public class PsCapsuleApiImpl implements PsCapsuleApi {

    @Autowired
    private PsCapsuleService psCapsuleService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public PsCapsuleModel findByCapsuleId(Integer capsuleId) {
        PsCapsule pojo = psCapsuleService.findByCapsuleId(capsuleId);
        if(OU.isBlack(pojo)){
            return null;
        }
        PsCapsuleModel model = new PsCapsuleModel();
        mapper.map(pojo, model);
        return model;

    }
}
