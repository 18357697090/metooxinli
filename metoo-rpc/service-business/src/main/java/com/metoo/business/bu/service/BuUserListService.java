package com.metoo.business.bu.service;

import com.metoo.business.bu.dao.entity.BuUserList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.metoo.pojo.bu.model.BuUserListModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-06
 */
public interface BuUserListService extends IService<BuUserList> {


    List<BuUserList> findUserList(Integer page);

    long userCount();
}
