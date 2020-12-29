package com.metoo.api.order;

import com.metoo.pojo.order.model.ImGiftRecordModel;

/**
 * <p>
 * 礼物记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImGiftRecordApi {

    void save(ImGiftRecordModel giftRecord);
}
