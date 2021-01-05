package com.metoo.api.tj;

import com.loongya.core.util.RE;
import com.metoo.pojo.old.model.TjUserInfoPojoModel;
import com.metoo.pojo.old.vo.ModifyUserIfoDTO;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.tj.vo.TjUserInfoVo;

/**
 * <p>
 * 用户个人信息表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface TjUserInfoApi {


    RE upLoadUserInfo(TjUserInfoVo vo);


    RE modifyUserInfo(Integer uid, ModifyUserIfoDTO modifyUserIfoDTO);

    RE userInfo(Integer uid1, Integer uid);

    TjUserInfoModel findByUid(Integer uid);
}
