package com.metoo.api.order;

import com.loongya.core.util.RE;
import com.metoo.pojo.order.model.PsCapsuleOrderModel;
import com.metoo.pojo.ps.vo.PsCapsuleVo;

/**
 * <p>
 * 用户购买胶囊记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsCapsuleOrderApi {

    PsCapsuleOrderModel findByUidAndCapsuleId(Integer uid, Integer capsuleId);

    RE pay(PsCapsuleVo vo);
}
