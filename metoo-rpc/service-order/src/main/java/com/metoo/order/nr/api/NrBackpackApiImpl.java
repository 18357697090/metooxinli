package com.metoo.order.nr.api;

import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.order.NrBackpackApi;
import com.metoo.api.tj.TjUserAccountApi;
import com.metoo.order.nr.dao.entity.NrBackpack;
import com.metoo.order.nr.dao.entity.NrGoods;
import com.metoo.order.nr.service.NrBackpackService;
import com.metoo.order.nr.service.NrGoodsService;
import com.metoo.pojo.old.vo.BackpackDTO;
import com.metoo.pojo.tj.model.TjUserAccountModel;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@DubboService
public class NrBackpackApiImpl implements NrBackpackApi {

    @Resource
    private NrBackpackService nrBackpackService;

    @Resource
    private NrGoodsService nrGoodsService;

    @DubboReference
    private TjUserAccountApi tjUserAccountApi;

    @Autowired
    private DozerBeanMapper mapper;

    @Override
    public RE backpack(Integer uid) {

        List<NrBackpack> backpackList = nrBackpackService.findByUid(uid);
        List<BackpackDTO> backpackDTOS = new ArrayList<>();
        List<NrGoods> goods = nrGoodsService.list();
        if (OU.isBlack(backpackList)) {
            return RE.noData();
        }
        for (NrBackpack backpack : backpackList){
            BackpackDTO backpackDTO = mapper.map(backpack,BackpackDTO.class);
            backpackDTO.setName(goods.get(backpackDTO.getType()-1).getName());
            backpackDTO.setPicture(goods.get(backpackDTO.getType()-1).getPicture());
            backpackDTO.setContent(goods.get(backpackDTO.getType()-1).getContent());
            backpackDTOS.add(backpackDTO);
        }
        if(OU.isBlack(backpackList)){
            return RE.noData();
        }
        return RE.ok(backpackList);
    }

    @Override
    public RE buy(Integer uid, Integer type) {

        TjUserAccountModel zh = tjUserAccountApi.findByUid(uid);
        NrGoods goods = nrGoodsService.findByType(type);
        List<NrBackpack> backpack = nrBackpackService.findByUid(uid);
        NrBackpack backpack01 = null;
        int i =zh.getBalance().compareTo(new BigDecimal(goods.getPrices()));
        if(i < 0){
            return RE.serviceFail("notEnoughMoney");
        }else {
            tjUserAccountApi.updateBalance(zh.getBalance().subtract(new BigDecimal(goods.getPrices())),uid);
            if(backpack==null){
                NrBackpack backpack1 = new NrBackpack();
                backpack1.setType(type);
                backpack1.setUid(uid);
                backpack1.setNumber(1);
                nrBackpackService.save(backpack1);
            }else {
                for (NrBackpack a : backpack){
                    if (a.getType().equals(type)){
                        backpack01 = a;
                    }
                }
                if (backpack01==null){
                    NrBackpack backpack1 = new NrBackpack();
                    backpack1.setType(type);
                    backpack1.setUid(uid);
                    backpack1.setNumber(1);
                    nrBackpackService.save(backpack1);
                }else {
                    nrBackpackService.updateGoodsNumber(backpack01.getNumber()+1,uid,type);
                }
            }
            return RE.ok();
        }
    }

    @Override
    public RE give(Integer uid, Integer type, Integer donee) {
        TjUserAccountModel zh = tjUserAccountApi.findByUid(uid);
        NrGoods goods = nrGoodsService.findByType(type);
        List<NrBackpack> backpack = nrBackpackService.findByUid(donee);
        NrBackpack backpack01 = null;
        int i =zh.getBalance().compareTo(new BigDecimal(goods.getPrices()));
        if(i < 0){
            return RE.serviceFail("notEnoughMoney");
        }else {
            tjUserAccountApi.updateBalance(zh.getBalance().subtract(new BigDecimal(goods.getPrices())),uid);
            if(backpack==null){
                NrBackpack backpack1 = new NrBackpack();
                backpack1.setType(type);
                backpack1.setUid(donee);
                backpack1.setNumber(1);
                nrBackpackService.save(backpack1);
            }else {
                for (NrBackpack a : backpack){
                    if (a.getType().equals(type)){
                        backpack01 = a;
                    }
                }
                if (backpack01==null){
                    NrBackpack backpack1 = new NrBackpack();
                    backpack1.setType(type);
                    backpack1.setUid(donee);
                    backpack1.setNumber(1);
                    nrBackpackService.save(backpack1);
                }else {
                    nrBackpackService.updateGoodsNumber(backpack01.getNumber()+1,donee,type);
                }
            }
            return RE.ok();
        }
    }
}
