package com.metoo.user.in.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loongya.core.util.CopyUtils;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.in.InAreaApi;
import com.metoo.pojo.in.model.InAreaModel;
import com.metoo.pojo.in.vo.InAreaVo;
import com.metoo.user.in.dao.entity.InArea;
import com.metoo.user.in.service.InAreaService;
import com.metoo.user.in.service.InSwiperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

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
public class InAreaApiImpl implements InAreaApi {

    @Resource
    private InAreaService inAreaService;


    @Override
    public RE initCoArea(InAreaVo vo) {
        // 获取json
        String classPath = this.getClass().getResource("/").getPath();
        BufferedReader br = null;
        FileWriter bw  = null;
        Map<String,Object> map = null;
        try {
            try {
                br = new BufferedReader(new FileReader(new File(classPath, "data.json")));
                String line = br.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null){
                    sb.append(line);
                    line = br.readLine();
                }
                JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                JSONObject provJson = jsonObject.getJSONObject("86");
                map = provJson.getInnerMap();
                JSONArray provList = new JSONArray();
                for(String provCode: map.keySet()){
                    InAreaModel provModel = new InAreaModel();
                    InArea prov = new InArea();
                    prov.setCode(provCode);
                    prov.setName((String) map.get(provCode));
                    prov.setPid(0);
                    prov.setLevel(1);
                    inAreaService.save(prov);
                    provModel = CopyUtils.copy(prov, provModel);
                    JSONArray cityList = new JSONArray();
                    Map<String, Object> cityMap = null;
                    // 获取市
                    JSONObject cityJson = jsonObject.getJSONObject(provCode);
                    if (OU.isBlack(cityJson)){
                        provList.add(provModel);
                        continue;
                    }
                    cityMap = cityJson.getInnerMap();
                    for(String cityCode: cityMap.keySet()){
                        InAreaModel cityModel = new InAreaModel();
                        InArea city = new InArea();
                        city.setCode(cityCode);
                        city.setLevel(2);
                        city.setPname(prov.getName());
                        city.setPid(prov.getId());
                        city.setPcode(prov.getCode());
                        StringBuilder pcodessb = new StringBuilder();
                        pcodessb.append(prov.getCode());
                        city.setPcodes(pcodessb.toString());
                        StringBuilder pnamessb = new StringBuilder();
                        pnamessb.append(prov.getName());
                        city.setPcodenames(pnamessb.toString());
                        city.setName((String) cityMap.get(cityCode));
                        inAreaService.save(city);
                        cityModel = CopyUtils.copy(city, cityModel);
                        JSONArray areaList = new JSONArray();
                        // 获取区
                        Map<String, Object> areaMap = null;
                        JSONObject areaJson = jsonObject.getJSONObject(cityCode);
                        if(OU.isBlack(areaJson)){
                            cityList.add(cityModel);
                            continue;
                        }
                        areaMap = areaJson.getInnerMap();
                        for(String areaCode: areaMap.keySet()){
                            InAreaModel areaModel = new InAreaModel();
                            InArea area = new InArea();
                            area.setCode(areaCode);
                            area.setLevel(3);
                            area.setPname(city.getName());
                            area.setPid(city.getId());
                            area.setPcode(city.getCode());
                            StringBuilder pcodesareasb = new StringBuilder();
                            pcodesareasb.append(prov.getCode());
                            pcodesareasb.append(",");
                            pcodesareasb.append(city.getCode());
                            area.setPcodes(pcodesareasb.toString());
                            StringBuilder pnamesareasb = new StringBuilder();
                            pnamesareasb.append(prov.getName());
                            pnamesareasb.append(",");
                            pnamesareasb.append(city.getName());
                            area.setPcodenames(pnamesareasb.toString());
                            area.setName((String) areaMap.get(areaCode));
                            inAreaService.save(area);
                            areaModel = CopyUtils.copy(area, areaModel);
                            JSONArray streetList = new JSONArray();
                            // 获取区
                            Map<String, Object> streetMap = null;
                            JSONObject streetJson = jsonObject.getJSONObject(areaCode);
                            if(OU.isBlack(streetJson)){
                                areaList.add(areaModel);
                                continue;
                            }
                            streetMap = streetJson.getInnerMap();
                            for(String streetCode: streetMap.keySet()){
                                InAreaModel streetModel = new InAreaModel();
                                InArea street = new InArea();
                                street.setCode(streetCode);
                                street.setLevel(4);
                                street.setPname(area.getName());
                                street.setPid(area.getId());
                                street.setPcode(area.getCode());
                                StringBuilder pcodesstreetsb = new StringBuilder();
                                pcodesstreetsb.append(prov.getCode());
                                pcodesstreetsb.append(",");
                                pcodesstreetsb.append(city.getCode());
                                pcodesstreetsb.append(",");
                                pcodesstreetsb.append(street.getCode());
                                street.setPcodes(pcodesstreetsb.toString());
                                StringBuilder pnamesstreetsb = new StringBuilder();
                                pnamesstreetsb.append(prov.getName());
                                pnamesstreetsb.append(",");
                                pnamesstreetsb.append(city.getName());
                                pnamesstreetsb.append(",");
                                pnamesstreetsb.append(street.getName());
                                street.setPcodenames(pnamesstreetsb.toString());
                                street.setName((String) streetMap.get(streetCode));
                                inAreaService.save(street);
                                streetModel = CopyUtils.copy(street, streetModel);
                                streetList.add(streetModel);
                            } // end street for
                            areaModel.setList(streetList);
                            areaList.add(areaModel);
                        } // end area for
                        cityModel.setList(areaList);
                        cityList.add(cityModel);
                    } // end city for
                    provModel.setList(cityList);
                    provList.add(provModel);
                } // end provfor
                log.error(provList.toJSONString());
                bw = new FileWriter(new File("E:\\A资料\\公司资料\\area.json"));
                bw.write(provList.toJSONString());
                bw.flush();
            }finally {
                if(OU.isNotBlack(br)|| OU.isNotBlack(bw)){
                    br.close();
                }
                if(OU.isNotBlack(bw)){
                    bw.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RE.ok();
    }
}
