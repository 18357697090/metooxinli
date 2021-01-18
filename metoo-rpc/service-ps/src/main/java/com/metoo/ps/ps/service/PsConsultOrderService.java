package com.metoo.ps.ps.service;

import com.metoo.ps.ps.dao.entity.PsConsultOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-07
 */
public interface PsConsultOrderService extends IService<PsConsultOrder> {

    PsConsultOrder UnfinishedConsult(Integer userId,Integer ConId);
}
