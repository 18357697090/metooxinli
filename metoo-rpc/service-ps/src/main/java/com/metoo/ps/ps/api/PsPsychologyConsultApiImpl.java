package com.metoo.ps.ps.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.ps.PsPsychologyConsultApi;
import com.metoo.pojo.ps.model.PsPsychologyConsultModel;
import com.metoo.ps.ps.dao.entity.PsPsychologyConsult;
import com.metoo.ps.ps.service.PsPsychologyConsultService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 心理咨询师表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
public class PsPsychologyConsultApiImpl implements PsPsychologyConsultApi {

    @Autowired
    private PsPsychologyConsultService psPsychologyConsultService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE psychologyConsults() {
        Pageable pageable = PageRequest.of(0,5, Sort.Direction.DESC,"prices");
        List<PsPsychologyConsult> list =  psPsychologyConsultService.findByOnLine(1,pageable);
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return RE.ok(
                list.stream().flatMap(e->{
                    return Stream.of(mapper.map(e, PsPsychologyConsultModel.class));
                }).collect(Collectors.toList())
        );
    }
}
