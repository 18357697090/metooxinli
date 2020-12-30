package com.metoo.api.im;

import com.loongya.core.util.RE;
import com.metoo.pojo.im.model.ImSaveUserMessageModel;

/**
 * <p>
 * 用户离线聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImSaveUserMessageApi {

    RE findByUidAndSendId(Integer uid, Integer sendId);

    void save(ImSaveUserMessageModel imSaveUserMessageModel);
}
