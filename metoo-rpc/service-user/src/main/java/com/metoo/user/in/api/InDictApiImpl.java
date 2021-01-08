package com.metoo.user.in.api;

import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.in.InDictApi;
import com.metoo.pojo.in.model.InDictModel;
import com.metoo.pojo.in.vo.InDictVo;
import com.metoo.user.in.dao.entity.InDict;
import com.metoo.user.in.service.InDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 首页轮播图 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
@Slf4j
public class InDictApiImpl implements InDictApi {

    @Resource
    private InDictService inDictService;


    @Override
    public RE levelList(InDictVo vo) {
        List<InDict> dictList = inDictService.findAllByPkey(ConstantUtil.LEVEL_DICT);
        if(OU.isBlack(dictList)){
            return RE.noData();
        }
        dictList.stream().flatMap(e->{
            InDictModel model = CopyUtils.copy(e, new InDictModel());
            model.setValue(OSSUtil.fillPath(model.getValue()));
            return Stream.of(model);
        }).collect(Collectors.toList());
        return RE.ok(dictList);
    }
}
