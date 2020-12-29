package com.metoo.im.im.service;

import com.metoo.im.im.dao.entity.ImGift;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 礼物表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImGiftService extends IService<ImGift> {
    ImGift findByGiftId(Integer giftId);

    List<ImGift> findAll();
}
