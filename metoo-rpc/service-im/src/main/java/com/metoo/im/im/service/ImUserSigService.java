package com.metoo.im.im.service;

import com.metoo.im.im.dao.entity.ImUserSig;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * <p>
 * 用户语音聊天权限表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface ImUserSigService extends IService<ImUserSig> {
    ImUserSig findByUid(Integer uid);

    int updatausersig(String usersig, Date date, Integer uid);
}
