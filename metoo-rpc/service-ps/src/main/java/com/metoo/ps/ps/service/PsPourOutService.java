package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.model.PsPourOutModel;
import com.metoo.ps.ps.dao.entity.PsPourOut;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 心理倾诉师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsPourOutService extends IService<PsPourOut> {

    List<PsPourOutModel> findByOnLine(Integer i, Pageable pageable);

    List<PsPourOutModel> findPourOutRand();
}
