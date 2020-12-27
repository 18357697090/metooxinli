package com.metoo.user.tj.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metoo.user.tj.dao.entity.TjUser;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author loongya
 * @since 2020-12-15
 */
public interface TjUserMapper extends BaseMapper<TjUser> {

    TjUser getTjUserById(Integer id);
}
