package com.metoo.im.im.api;


import com.metoo.api.im.ImApplyToJoinRoomApi;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 申请加入聊天室记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class ImApplyToJoinRoomApiImpl implements ImApplyToJoinRoomApi {

}
