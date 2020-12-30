package com.metoo.user.tj.service.impl;

import com.metoo.user.tj.dao.entity.TjLevelPicture;
import com.metoo.user.tj.dao.mapper.TjLevelPictureMapper;
import com.metoo.user.tj.dao.repository.TjLevelPictureRepository;
import com.metoo.user.tj.service.TjLevelPictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户等级图标表 服务实现类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Service
public class TjLevelPictureServiceImpl extends ServiceImpl<TjLevelPictureMapper, TjLevelPicture> implements TjLevelPictureService {

    @Autowired
    private TjLevelPictureRepository tjLevelPictureRepository;

    @Override
    public TjLevelPicture findByLevel(Integer dw) {
        return tjLevelPictureRepository.findByLevel(dw);
    }
}
