package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleProblemApi;
import com.metoo.pojo.old.model.Problems;
import com.metoo.pojo.ps.model.PsScaleProblemModel;
import com.metoo.ps.ps.service.PsScaleOptionsService;
import com.metoo.ps.ps.service.PsScaleProblemService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 心理测量量表题目表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsScaleProblemApiImpl implements PsScaleProblemApi {

    @Autowired
    private PsScaleOptionsService psScaleOptionsService;

    @Autowired
    private PsScaleProblemService psProblemService;

    @Override
    public RE problem(Integer scaleId) {
        Problems problems=new Problems();
        problems.setOptions(psScaleOptionsService.findByScaleId(scaleId));
        problems.setProblems(psProblemService.findByScaleId(scaleId));
        return RE.ok(problems);
    }

    @Override
    public List<PsScaleProblemModel> findByScaleId(Integer scaleId) {
        return psProblemService.findByScaleId(scaleId);
    }
}
