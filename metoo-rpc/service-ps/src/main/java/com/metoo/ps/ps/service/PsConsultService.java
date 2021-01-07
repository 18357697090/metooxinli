package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.model.PsConsultModel;
import com.metoo.ps.ps.dao.entity.PsConsult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <p>
 * 心理咨询师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
public interface PsConsultService extends IService<PsConsult> {

    List<PsConsult> findByOnLine(Integer i, Pageable pageable);

    List<PsConsultModel> findPsychologyConsultRand();
}
