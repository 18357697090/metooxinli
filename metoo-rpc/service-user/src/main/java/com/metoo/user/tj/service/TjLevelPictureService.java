package com.metoo.user.tj.service;

import com.metoo.user.tj.dao.entity.TjLevelPicture;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户等级图标表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjLevelPictureService extends IService<TjLevelPicture> {

    TjLevelPicture findByLevel(Integer dw);
}
