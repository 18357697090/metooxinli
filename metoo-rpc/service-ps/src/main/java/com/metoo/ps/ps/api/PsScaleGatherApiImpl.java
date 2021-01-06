package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsScaleGatherApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.pojo.login.enums.AuthEnum;
import com.metoo.pojo.ps.model.PsScaleGatherModel;
import com.metoo.pojo.ps.vo.PsScaleVo;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.ps.ps.dao.entity.PsScaleGather;
import com.metoo.ps.ps.service.PsScaleGatherService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理测量量表集合表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class PsScaleGatherApiImpl implements PsScaleGatherApi {

    @Autowired
    private PsScaleGatherService psScaleGatherService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;


    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE clgather(PsScaleVo vo) {
        TjUserInfoModel userInfo = tjUserInfoApi.findByUid(vo.getUserId());
        if(OU.isBlack(userInfo)){
            return RE.fail(AuthEnum.LOGIN_TIMEOUT);
        }
        int level=OU.isBlack(userInfo.getLevel()) ? 1 : userInfo.getLevel();
        List<PsScaleGather> scaleGathers =new ArrayList<>();
        scaleGathers.add(psScaleGatherService.findByScaleGatherId(level));
        scaleGathers.add(psScaleGatherService.findByScaleGatherId(101));
        scaleGathers.add(psScaleGatherService.findByScaleGatherId(102));
        if(OU.isBlack(scaleGathers)){
            return RE.noData();
        }
        return RE.ok(scaleGathers);
    }

    @Override
    public RE clgaherall() {

        List<PsScaleGather> list = psScaleGatherService.findByScaleGatherIdAll();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(list.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsScaleGatherModel.class));
        }).collect(Collectors.toList()));
    }
}
