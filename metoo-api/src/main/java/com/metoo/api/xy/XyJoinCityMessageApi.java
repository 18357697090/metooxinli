package com.metoo.api.xy;

import com.loongya.core.util.RE;
import com.metoo.pojo.xy.vo.XyJoinCityMessageVo;

/**
 * <p>
 * 申请加入城消息记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface XyJoinCityMessageApi{

    RE joinCity(Integer uid, XyJoinCityMessageVo vo);
}
