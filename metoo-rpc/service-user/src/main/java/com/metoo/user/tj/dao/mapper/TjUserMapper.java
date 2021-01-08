package com.metoo.user.tj.dao.mapper;

import com.metoo.pojo.login.model.LoginModel;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.user.tj.dao.entity.TjUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserMapper extends BaseMapper<TjUser> {

    LoginModel login(LoginVo vo);

    Integer findByExtendId(Integer extendId);
}
