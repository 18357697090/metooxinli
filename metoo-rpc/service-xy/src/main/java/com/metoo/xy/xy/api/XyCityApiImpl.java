package com.metoo.xy.xy.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.api.xy.XyCityApi;
import com.metoo.api.xy.XyCountryApi;
import com.metoo.api.xy.XyMyRoomApi;
import com.metoo.pojo.old.vo.ReturnCityDTO;
import com.metoo.xy.xy.dao.entity.XyCity;
import com.metoo.xy.xy.dao.entity.XyCountry;
import com.metoo.xy.xy.service.XyCityService;
import com.metoo.xy.xy.service.XyCountryService;
import com.metoo.xy.xy.service.XyMyRoomService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 城表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class XyCityApiImpl implements XyCityApi {

    @Autowired
    private XyCityService xyCityService;

    @Autowired
    private XyCountryService xyCountryService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @Autowired
    private XyMyRoomService xyMyRoomService;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE getCity(Integer uid, Integer countryId) {

        List<XyCity> cities = xyCityService.findByCountryId(countryId);
        XyCountry country = xyCountryService.findByCountryId(countryId);
        ReturnCityDTO returnCityDTO1 = new ReturnCityDTO();
        returnCityDTO1.setCityId(countryId);
        List<ReturnCityDTO> returnCityDTOS = new ArrayList<>();
        for (XyCity city : cities){
            ReturnCityDTO returnCityDTO = mapper.map(city,ReturnCityDTO.class);
            returnCityDTO.setUsername(tjUserInfoApi.findByUid(xyMyRoomService.findByMyRoomIdAndIsHost(city.getCityId()).getUid()).getName());
            returnCityDTOS.add(returnCityDTO);
        }
        if(OU.isBlack(returnCityDTOS)){
            return RE.noData();
        }
        return RE.ok(returnCityDTOS);
    }
}
