package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImUserMessage;
import com.metoo.im.im.dao.mapper.ImUserMessageMapper;
import com.metoo.im.im.service.ImUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户聊天记录表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImUserMessageServiceImpl extends ServiceImpl<ImUserMessageMapper, ImUserMessage> implements ImUserMessageService {

}
