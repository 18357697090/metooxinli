package com.metoo.api.order;

import com.loongya.core.util.RE;

/**
 * <p>
 * 用户购买胶囊记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsUserBuyCapsuleApi {

    RE pay(Integer uid, Integer capsuleId);
}
