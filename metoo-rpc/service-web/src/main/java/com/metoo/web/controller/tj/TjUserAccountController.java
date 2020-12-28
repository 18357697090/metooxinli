package com.metoo.web.controller.tj;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户账户表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/tj/tj-user-account")
public class TjUserAccountController {

    @GetMapping("/me")
    public MeDTO me(@RequestHeader("UID")Integer uid){
        System.out.println(uid);
        Zh zh = zhDao.findByUid(uid);
        UserInfo userInfo = userInfoDao.findByUid(uid);
        MeDTO meDTO = new MeDTO();
        meDTO.setMotto(userInfo.getMotto());
        meDTO.setPicture(userInfo.getPicture());
        meDTO.setLevel(userInfo.getDw());
        meDTO.setName(userInfo.getName());
        meDTO.setActiveIntegral(zh.getActiveIntegral());
        meDTO.setPsychologyIntegral(zh.getPsychologyIntegral());
        meDTO.setBalance(zh.getBalance());
        return meDTO;
    }

    //获取个人账户信息
    @GetMapping("/findzh")
    public Zh findzh(Integer uid){
        return zhDao.findByUid(uid);
    }


    //查找账户余额
    @GetMapping("/findBalance")
    public BigDecimal findBalance(@RequestHeader("UID") Integer uid){
        Zh zh=zhDao.findByUid(uid);
        return zh.getBalance();
    }



}
