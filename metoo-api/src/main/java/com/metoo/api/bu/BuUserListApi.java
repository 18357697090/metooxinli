package com.metoo.api.bu;

import com.loongya.core.util.RE;
import com.metoo.pojo.bu.model.BuUserListModel;
import com.metoo.pojo.in.vo.InAreaVo;

import java.util.List;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface BuUserListApi {

    List<BuUserListModel> findUserList(Integer page);

    long userCount();
}
