package com.metoo.user.tj.api;

import com.metoo.api.tj.TjUserAccountCoinDetailApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.pojo.tj.model.TjUserAccountCoinDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountDetailModel;
import com.metoo.user.tj.dao.entity.TjUserAccountCoinDetail;
import com.metoo.user.tj.dao.entity.TjUserAccountDetail;
import com.metoo.user.tj.service.TjUserAccountCoinDetailService;
import com.metoo.user.tj.service.TjUserAccountDetailService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 用户消费记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class TjUserAccountCoinDetailApiImpl implements TjUserAccountCoinDetailApi {

    @Autowired
    private TjUserAccountCoinDetailService tjUserAccountCoinDetailService;


    @Override
    public void insertDetails(TjUserAccountCoinDetailModel acModel) {
        TjUserAccountCoinDetail po = new TjUserAccountCoinDetail();
        po.setUid(acModel.getUid());
        po.setTypeName(acModel.getTypeName());
        po.setRemark(acModel.getRemark());
        po.setPrice(acModel.getPrice());
        po.setPrePric(acModel.getPrePrice());
        po.setAfterPrice(acModel.getAfterPrice());
        po.setCreateTime(new Date());
        po.setType(acModel.getType());
        po.setContent(acModel.getContent());
        po.setAccountId(acModel.getAccountId());
        tjUserAccountCoinDetailService.save(po);
    }
}
