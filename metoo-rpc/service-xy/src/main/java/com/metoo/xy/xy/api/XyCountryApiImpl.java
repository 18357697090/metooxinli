package com.metoo.xy.xy.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.api.tj.TjUserAccountDetailApi;
import com.metoo.api.tj.TjUserInfoApi;
import com.metoo.api.xy.XyCountryApi;
import com.metoo.pojo.old.vo.BuildCountryDTO;
import com.metoo.pojo.old.vo.ReturnCountryDTO;
import com.metoo.pojo.tj.model.TjUserAccountDetailModel;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import com.metoo.pojo.tj.model.TjUserInfoModel;
import com.metoo.pojo.xy.vo.XyCountryVo;
import com.metoo.tools.CreateID;
import com.metoo.xy.xy.dao.entity.XyCity;
import com.metoo.xy.xy.dao.entity.XyCountry;
import com.metoo.xy.xy.dao.entity.XyMyRoom;
import com.metoo.xy.xy.service.XyCityService;
import com.metoo.xy.xy.service.XyCountryService;
import com.metoo.xy.xy.service.XyMyRoomService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 国度表 服务类
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@Component
@DubboService
@Transactional
public class XyCountryApiImpl implements XyCountryApi {

    @Autowired
    private XyCountryService xyCountryService;

    @Autowired
    private XyCityService xyCityService;

    @Autowired
    private XyMyRoomService xyMyRoomService;
//    @Autowired
//    private XyRaceService xyRaceService;

    @DubboReference
    private TjUserInfoApi tjUserInfoApi;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @DubboReference
    private TjUserAccountDetailApi tjUserAccountDetailApi;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE buildCountry(XyCountryVo vo) {

            TjUserInfoModel userInfo = tjUserInfoApi.findByUid(vo.getUid());
            if (userInfo.getLevel()<4  ){
                return RE.fail("等级不够，需要钻石段位及以上才可以创建国度");
            }
            BigDecimal bigDecimal = new BigDecimal("5000");
            TjUserAccountModel zh = tjUserAccountApi.findByUid(vo.getUid());
            BigDecimal balance = zh.getBalance().subtract(bigDecimal);
            if(balance.compareTo(BigDecimal.ZERO)<0){
                return RE.fail("你的活跃积分不足");
            }else {
                String name = vo.getName();
                if (name.length() > 6) {
                    return RE.fail("填写名称太长");
                }
                XyCountry country2 = xyCountryService.findByName(vo.getName());
                if (country2 != null) {
                    return RE.fail("国度名字已存在");
                }
                tjUserAccountApi.updateBalance(bigDecimal, vo.getUid());
                XyCountry country = mapper.map(vo, XyCountry.class);
                int countryId = CreateID.create();
                XyCountry country1 = xyCountryService.findByCountryId(countryId);
                XyCity city = xyCityService.findByCityId(countryId);
                while (country1 != null && city != null) {
                    countryId = CreateID.create();
                    city = xyCityService.findByCityId(countryId);
                    country1 = xyCountryService.findByCountryId(countryId);
                }
//                country.setCountryId(countryId);
//                country.setState(1);
                xyCountryService.save(country);
                XyMyRoom myRoom = new XyMyRoom();
                myRoom.setUid(vo.getUid());
                myRoom.setCountryId(countryId);
                myRoom.setType(2);
                xyMyRoomService.save(myRoom);
                TjUserAccountDetailModel zhRecord = new TjUserAccountDetailModel();
                zhRecord.setUid(vo.getUid());
                zhRecord.setPrice(bigDecimal);
                zhRecord.setRemark("创建国度");
                zhRecord.setContent(vo.getName());
                tjUserAccountDetailApi.save(zhRecord);
                return RE.ok("创建成功");
            }
    }

    @Override
    public RE getCountry(Integer uid, Integer raceId) {

//        XyRace race =  xyRaceService.findByRaceId(raceId);
//        ReturnCountryDTO returnCountryDTO = mapper.map(race,ReturnCountryDTO.class);
//        returnCountryDTO.setCountryId(raceId);
//        returnCountryDTO.setStatusBar(2);
//        returnCountryDTO.setUserName(tjUserInfoApi.findByUid(xyMyRoomService.findByMyRoomIdAndIsHost(raceId).getUid()).getNickName());
        List<ReturnCountryDTO> returnCountryDTOS = new ArrayList<>();
//        returnCountryDTOS.add(returnCountryDTO);
        List<XyCountry> countries = xyCountryService.findByRaceId(raceId);
        for (XyCountry country : countries) {
            ReturnCountryDTO returnCountryDTO1 = mapper.map(country,ReturnCountryDTO.class);
//            returnCountryDTO1.setUserName(tjUserInfoApi.findByUid(xyMyRoomService.findByMyRoomIdAndIsHost(country.getCountryId()).getUid()).getNickName());
            returnCountryDTOS.add(returnCountryDTO1);
        }
        if(OU.isBlack(returnCountryDTOS)){
            return RE.noData();
        }
        return RE.ok(returnCountryDTOS);
    }
}
