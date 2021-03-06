package com.metoo.api.tj;

import com.loongya.core.util.RE;
import com.metoo.pojo.login.vo.LoginUploadPasswordVo;
import com.metoo.pojo.login.vo.LoginVo;
import com.metoo.pojo.old.model.LoginPojo;
import com.metoo.pojo.old.model.SecretGuardPojo;
import com.metoo.pojo.tj.model.TjUserModel;
import com.metoo.pojo.wechat.tj.login.model.WechatLoginModel;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserApi {


    RE logIn(LoginVo vo);

    RE register(LoginVo vo);

    RE modifyPassword(LoginUploadPasswordVo vo);

    RE getUserInfo(Integer userId);

    TjUserModel getTjUser(Integer id);

    RE fendFriend(Integer uid, String name);

    RE findUserIdByExtendId(Integer extendId);

    RE findByOpenId(String openId);

    RE saveToken(WechatLoginModel model);
}
