package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsCapsuleImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
public interface PsCapsuleImgService extends IService<PsCapsuleImg> {

    List<PsCapsuleImg> findImgListByCapId(Integer id);
}
