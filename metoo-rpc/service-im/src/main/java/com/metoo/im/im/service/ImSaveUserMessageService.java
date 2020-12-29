package com.metoo.im.im.service;

import com.metoo.im.im.dao.entity.ImSaveUserMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户离线聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImSaveUserMessageService extends IService<ImSaveUserMessage> {
    List<ImSaveUserMessage> findByUidAndSendId(Integer uid, Integer sendId);
}
