package com.metoo.im.im.service;

import com.metoo.im.im.dao.entity.ImUserMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 用户聊天记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImUserMessageService extends IService<ImUserMessage> {
    int updateState(int uid);

    int deleteByUid(int uid);

    int aaa(@Param("name") Integer name);

    List<Object> bbb(@Param("name") Integer name);

    List<ImUserMessage> uid(Integer uid);
}
