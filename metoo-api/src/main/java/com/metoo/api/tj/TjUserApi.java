package com.metoo.api.tj;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
import com.metoo.pojo.old.model.signInPojo;

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

    RE logIn(signInPojo signInPojo);

    RE modifyPassword(SecretGuardPojo secretGuardPojo);
}
