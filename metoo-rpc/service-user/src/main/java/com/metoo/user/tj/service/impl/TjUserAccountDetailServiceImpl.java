package com.metoo.user.tj.service.impl;

import com.metoo.pojo.tj.model.TjUserAccountDetailAddDetailModel;
import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import com.metoo.user.tj.dao.mapper.TjUserAccountDetailMapper;
import com.metoo.user.tj.service.TjUserAccountDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户消费记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjUserAccountDetailServiceImpl extends ServiceImpl<TjUserAccountDetailMapper, TjUserAccountDetail> implements TjUserAccountDetailService {

    @Override
    public void insertDetails(TjUserAccountDetailAddDetailModel acModel) {
        TjUserAccountDetail pojo = new TjUserAccountDetail();
        pojo.setUid(acModel.getUid());
        pojo.setTypeName(acModel.getTypeName());
        pojo.setRemark(acModel.getRemark());
        pojo.setType(acModel.getType());
        pojo.setPrice(acModel.getPrice());
        pojo.setPrePrice(acModel.getPrePrice());
        pojo.setAfterPrice(acModel.getAfterPrice());
        pojo.setCreateTime(new Date());
        pojo.setContent(acModel.getContent());
        pojo.setAccountId(acModel.getAccountId());
        this.save(pojo);
    }
}
