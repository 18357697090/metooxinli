package com.metoo.order.nr.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.loongya.core.util.REPage;
import com.loongya.core.util.aliyun.OSSUtil;
import com.metoo.api.nr.NrGoodsApi;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.service.NrGoodsService;
import com.metoo.pojo.nr.model.NrGoodsModel;
import com.metoo.pojo.nr.vo.NrGoodsVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class NrGoodsApiImpl implements NrGoodsApi {

    @Resource
    private NrGoodsService nrGoodsService;

    @Override
    public RE getGoodsList(NrGoodsVo vo)  {
        Page<NrGoods> page = new Page<>(vo.getPagenum(), vo.getPagesize());
        page = nrGoodsService.page(page);
        List<NrGoods> list = page.getRecords();
        if(OU.isBlack(list)){
            return RE.noData();
        }
        return REPage.ok(vo.getPagenum(), vo.getPagesize(), page.getTotal(), list.stream().flatMap(e->{
            NrGoodsModel model= CopyUtils.copy(e, new NrGoodsModel());
            model.setImg(OSSUtil.fillPath(e.getImg()));
            return Stream.of(model);
        }).collect(Collectors.toList()));
    }
}
