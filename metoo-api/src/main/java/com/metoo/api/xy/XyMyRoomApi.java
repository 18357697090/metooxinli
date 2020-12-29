package com.metoo.api.xy;

import com.loongya.core.util.RE;

/**
 * <p>
 * 我的加入的聊天室表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyMyRoomApi {

    RE myRoom(Integer uid);

    RE getMyCountryList(Integer uid);

    RE joinAudioRoom(Integer uid, Integer audioRoomId);
}
