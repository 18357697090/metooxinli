package com.metoo.ps.ps.service.impl;

import com.loongya.core.util.OU;
import com.metoo.pojo.bu.model.BuUserListModel;
import com.metoo.pojo.ps.model.PsScaleResultModel;
import com.metoo.ps.ps.dao.entity.PsScaleResult;
import com.metoo.ps.ps.dao.mapper.PsScaleResultMapper;
import com.metoo.ps.ps.dao.repository.PsScaleResultRepository;
import com.metoo.ps.ps.service.PsScaleResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loongya
 * @since 2021-01-20
 */
@Service
public class PsScaleResultServiceImpl extends ServiceImpl<PsScaleResultMapper, PsScaleResult> implements PsScaleResultService {

    @Autowired
    private PsScaleResultRepository psScaleResultRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<PsScaleResultModel> getScaleResult(Integer scaleId) {
        List<PsScaleResult> psScaleResults = psScaleResultRepository.findByScaleId(scaleId);
        if (OU.isBlack(psScaleResults)){
            return null;
        }
        return psScaleResults.stream().flatMap(e->{
            return Stream.of(mapper.map(e, PsScaleResultModel.class));
        }).collect(Collectors.toList());
    }
}
