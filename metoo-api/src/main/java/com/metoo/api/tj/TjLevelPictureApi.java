package com.metoo.api.tj;

import com.metoo.pojo.tj.model.TjLevelPictureModel;

/**
 * <p>
 * 用户等级图标表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjLevelPictureApi{

    TjLevelPictureModel findByLevel(Integer dw);
}
