package com.metoo.web.controller.order;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户背包商品表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/nr/nr-backpack")
public class NrBackpackController {

    @GetMapping("/backpack")
    public List<BackpackDTO> backpack(@RequestHeader("UID")Integer uid ){
        List<Backpack> backpacks = backpackDao.findByUid(uid);
        List<BackpackDTO> backpackDTOS = new ArrayList<>();
        List<Goods> goods = goodsDao.findAll();
        if (backpacks!=null){
            for (Backpack backpack : backpacks){
                BackpackDTO backpackDTO = mapper.map(backpack,BackpackDTO.class);
                backpackDTO.setName(goods.get(backpackDTO.getType()-1).getName());
                backpackDTO.setPicture(goods.get(backpackDTO.getType()-1).getPicture());
                backpackDTO.setContent(goods.get(backpackDTO.getType()-1).getContent());
                backpackDTOS.add(backpackDTO);
            }
        }

        return backpackDTOS;
    }


    @GetMapping("/buy")
    public String buy(@RequestHeader("UID")Integer uid,Integer type){
        Zh zh = zhDao.findByUid(uid);
        Goods goods = goodsDao.findByType(type);
        List<Backpack> backpack = backpackDao.findByUid(uid);
        Backpack backpack01 = null;
        int i =zh.getBalance().compareTo(goods.getPrices());
        if(i < 0){
            return "notEnoughMoney";
        }else {
            zhDao.updateBalance(zh.getBalance().subtract(goods.getPrices()),uid);
            if(backpack==null){
                Backpack backpack1 = new Backpack();
                backpack1.setType(type);
                backpack1.setUid(uid);
                backpack1.setNumber(1);
                backpackDao.save(backpack1);
            }else {
                for (Backpack a : backpack){
                    if (a.getType().equals(type)){
                        backpack01 = a;
                    }
                }
                if (backpack01==null){
                    Backpack backpack1 = new Backpack();
                    backpack1.setType(type);
                    backpack1.setUid(uid);
                    backpack1.setNumber(1);
                    backpackDao.save(backpack1);
                }else {
                    backpackDao.updateGoodsNumber(backpack01.getNumber()+1,uid,type);
                }
            }
            return "success";
        }
    }



    @GetMapping("/give")
    public String give(@RequestHeader("UID")Integer uid,Integer type,Integer donee){
        Zh zh = zhDao.findByUid(uid);
        Goods goods = goodsDao.findByType(type);
        List<Backpack> backpack = backpackDao.findByUid(donee);
        Backpack backpack01 = null;
        int i =zh.getBalance().compareTo(goods.getPrices());
        if(i < 0){
            return "notEnoughMoney";
        }else {
            zhDao.updateBalance(zh.getBalance().subtract(goods.getPrices()),uid);
            if(backpack==null){
                Backpack backpack1 = new Backpack();
                backpack1.setType(type);
                backpack1.setUid(donee);
                backpack1.setNumber(1);
                backpackDao.save(backpack1);
            }else {
                for (Backpack a : backpack){
                    if (a.getType().equals(type)){
                        backpack01 = a;
                    }
                }
                if (backpack01==null){
                    Backpack backpack1 = new Backpack();
                    backpack1.setType(type);
                    backpack1.setUid(donee);
                    backpack1.setNumber(1);
                    backpackDao.save(backpack1);
                }else {
                    backpackDao.updateGoodsNumber(backpack01.getNumber()+1,donee,type);
                }
            }
            return "success";
        }
    }



}
