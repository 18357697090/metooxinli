package com.metoo.order.im.api;

import com.metoo.api.order.ImGiftRecordApi;
import com.metoo.order.im.dao.entity.ImGiftRecord;
import com.metoo.order.im.service.ImGiftRecordService;
import com.metoo.pojo.order.model.ImGiftRecordModel;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 礼物记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class ImGiftRecordApiImpl implements ImGiftRecordApi {

    @Autowired
    private ImGiftRecordService imGiftRecordService;
    @Autowired
    private Mapper mapper;

    @Override
    public void save(ImGiftRecordModel giftRecord) {
        ImGiftRecord imGiftRecord = mapper.map(giftRecord,ImGiftRecord.class);
        imGiftRecordService.save(imGiftRecord);
    }
}
