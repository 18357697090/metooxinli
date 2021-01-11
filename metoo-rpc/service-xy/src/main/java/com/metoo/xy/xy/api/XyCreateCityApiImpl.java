package com.metoo.xy.xy.api;

import com.loongya.core.util.RE;
import com.metoo.api.xy.XyCreateCityApi;
import com.metoo.pojo.xy.vo.XyCreateCityVo;
import com.metoo.xy.xy.dao.entity.XyCreateCity;
import com.metoo.xy.xy.service.XyCityService;
import com.metoo.xy.xy.service.XyCreateCityService;
import com.metoo.xy.xy.service.XyMyRoomService;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 申请加入城消息记录表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class XyCreateCityApiImpl implements XyCreateCityApi {

    @Autowired
    private XyCreateCityService xyCreateCityService;

    @Autowired
    private XyCityService xyCityService;

    @Autowired
    private XyMyRoomService xyMyRoomService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE joinCity(Integer uid, XyCreateCityVo vo) {
        if (vo.getCityHostId()==null||vo.getMessage()==null){
            return RE.fail("填写信息不完全");
        }

        XyCreateCity cityMessage = mapper.map(vo, XyCreateCity.class);
        cityMessage.setUid(uid);
        xyCreateCityService.save(cityMessage);
        return RE.ok("申请加入成功");
    }
}
