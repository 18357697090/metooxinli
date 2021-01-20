package com.metoo.ps.ps.service;

import com.metoo.pojo.ps.model.PsScaleResultModel;
import com.metoo.ps.ps.dao.entity.PsScaleResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author loongya
 * @since 2021-01-20
 */
public interface PsScaleResultService extends IService<PsScaleResult> {


    List<PsScaleResultModel> PsScaleResultModel(Integer scaleId);
}
