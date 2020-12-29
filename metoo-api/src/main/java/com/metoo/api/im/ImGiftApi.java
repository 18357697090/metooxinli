package com.metoo.api.im;


import com.loongya.core.util.RE;

/**
 * <p>
 * 礼物表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImGiftApi {

    RE givingGift(Integer uid, Integer acceptedId, Integer giftId, String number);

    RE getGiftList();
}
