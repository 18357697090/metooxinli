package com.metoo.api.ps;

import com.metoo.pojo.ps.model.PsCapsuleModel;

/**
 * <p>
 * 心理倾诉胶囊（备忘录功能）表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsCapsuleApi {

    PsCapsuleModel findByCapsuleId(Integer capsuleId);
}
