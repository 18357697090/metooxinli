package com.metoo.api.im;


import com.loongya.core.util.RE;

/**
 * <p>
 * 用户语音聊天权限表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImUserSigApi {

    RE getusersig(Integer uid);
}
