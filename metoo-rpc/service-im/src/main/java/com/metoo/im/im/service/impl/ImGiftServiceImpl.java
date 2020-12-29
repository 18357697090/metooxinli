package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImGift;
import com.metoo.im.im.dao.mapper.ImGiftMapper;
import com.metoo.im.im.dao.repository.ImGiftRepository;
import com.metoo.im.im.service.ImGiftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 礼物表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImGiftServiceImpl extends ServiceImpl<ImGiftMapper, ImGift> implements ImGiftService {

    @Autowired
    private ImGiftRepository imGiftRepository;

    @Override
    public ImGift findByGiftId(Integer giftId) {
        return imGiftRepository.findByGiftId(giftId);
    }
}
