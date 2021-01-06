package com.metoo.api.im;

import com.loongya.core.util.RE;
import com.metoo.pojo.im.model.ImUserMessageModel;

import java.util.List;

/**
 * <p>
 * 用户聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */

public interface ImUserMessageApi {

    RE uid(Integer uid);

    void save(ImUserMessageModel userMessage);

    void updateState(Integer uid);

    void deleteByUid(Integer uid);

    List<ImUserMessageModel> uidx(Integer uid);
}
