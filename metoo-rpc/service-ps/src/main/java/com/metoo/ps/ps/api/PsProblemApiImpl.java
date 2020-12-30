package com.metoo.ps.ps.api;

import com.loongya.core.util.RE;
import com.metoo.api.ps.PsProblemApi;
import com.metoo.pojo.old.model.Problems;
import com.metoo.pojo.ps.model.PsProblemModel;
import com.metoo.ps.ps.service.PsOptionsService;
import com.metoo.ps.ps.service.PsProblemService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class PsProblemApiImpl implements PsProblemApi {

    @Autowired
    private PsOptionsService psOptionsService;

    @Autowired
    private PsProblemService psProblemService;

    @Override
    public RE problem(Integer scaleId) {
        Problems problems=new Problems();
        problems.setOptions(psOptionsService.findByScaleId(scaleId));
        problems.setProblems(psProblemService.findByScaleId(scaleId));
        return RE.ok(problems);
    }

    @Override
    public List<PsProblemModel> findByScaleId(Integer scaleId) {
        return psProblemService.findByScaleId(scaleId);
    }
}
