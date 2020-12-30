package com.metoo.api.tj;

import com.loongya.core.util.RE;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserApi {

    RE fendFriend(Integer uid, String name);

    RE register(LoginPojo loginPojo);

    RE logIn(LoginVo vo);

    RE modifyPassword(SecretGuardPojo secretGuardPojo);

    RE findUserById(Integer userId);
}
