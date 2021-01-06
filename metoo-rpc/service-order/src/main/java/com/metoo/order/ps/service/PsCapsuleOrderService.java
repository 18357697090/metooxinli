package com.metoo.order.ps.service;

import com.metoo.order.ps.dao.entity.PsCapsuleOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metoo.pojo.order.model.PsCapsuleOrderModel;

/**
 * <p>
 * 用户购买胶囊记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsCapsuleOrderService extends IService<PsCapsuleOrder> {

    PsCapsuleOrderModel findByUidAndCapsuleId(Integer uid, Integer capsuleId);
}
