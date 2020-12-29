package com.metoo.im.im.service.impl;

import com.metoo.im.im.dao.entity.ImUserSig;
import com.metoo.im.im.dao.mapper.ImUserSigMapper;
import com.metoo.im.im.dao.repository.ImUserSigRepository;
import com.metoo.im.im.service.ImUserSigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户语音聊天权限表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class ImUserSigServiceImpl extends ServiceImpl<ImUserSigMapper, ImUserSig> implements ImUserSigService {

    @Autowired
    private ImUserSigRepository imUserSigRepository;

    @Override
    public ImUserSig findByUid(Integer uid) {
        return imUserSigRepository.findByUid(uid);
    }

    @Override
    public int updatausersig(String usersig, Date date, Integer uid) {
        return imUserSigRepository.updatausersig(usersig,date,uid);
    }
}
